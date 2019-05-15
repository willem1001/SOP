pipeline {
    agent any

    stages {
		stage('Initialize') {
			def JAVA_HOME = tool 'my_jdk'
			def MAVEN_HOME  = tool 'my_maven'
			env.PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
    }
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