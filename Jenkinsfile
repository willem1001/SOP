pipeline {
    agent any

    stages {
		stage('Initialize') {
			def mavenHome  = tool 'my_maven'
			env.PATH = "${mavenHome}/bin:${env.PATH}"
		}
        stage('Build') {
            steps {
                echo 'Building..'
				sh 'mvn package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}