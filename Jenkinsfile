pipeline {
    agent any
	
	tools {
		maven 'my_maven'
	}
	
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
				sh 'mvn package'
            }
        }
		/*stage('SonarQube analysis') {
		steps {
        script {
          scannerHome = tool 'my_sonarqube'
        }
        withSonarQubeEnv('my_sonarqubeserver') {
          sh "${scannerHome}/bin/sonar-scanner"
        }
      }
    }*/
        stage('Test') {
            steps { 
                echo 'Testing..'
				sh 'mvn test'
            }
        }
        
		stage('Create Dockerfile'){
			steps {
				sh 'docker --version'
				writeFile(file: 'Dockerfile', text: 'FROM payara/server-full \nCOPY /var/jenkins_home/workspace/Kwetter_Pipeline_master/target/ $DEPLOY_DIR', encoding: 'UTF-8')
				sh 'echo Done.writing'
			}
		}
        stage('Run Dockerfile'){
            steps {
                sh 'docker build --tag=payarasop /var/jenkins_home/workspace/Kwetter_Pipeline_master'
                sh 'echo donebuilding'
			    sh 'docker run -p 8080:8080 -p 4848:4848 payarasop'
            }
            
        }
    }
	
	
	post {
	    always {
           emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
               recipientProviders: [[$class: 'DevelopersRecipientProvider']],
               subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
			   
		    archiveArtifacts "target/**/*"
            junit 'target/surefire-reports/*.xml'
		}
		success {
           mail to:"w1001.testmail@gmail.com", subject:"SUCCESS: ${currentBuild.fullDisplayName}", body: "Yay, we passed."
		}
		failure {
           mail to:"w1001.testmail@gmail.com", subject:"FAILURE: ${currentBuild.fullDisplayName}", body: "Boo, we failed."
		}
	}
}