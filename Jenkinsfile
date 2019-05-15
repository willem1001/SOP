pipeline {
    agent any

    stages {
			stage('Initialize'){
			steps{
			def dockerHome = tool 'my_docker'
			env.PATH = "${dockerHome}/bin:${env.PATH}"
			}
		}
        stage('Build') {
            steps {
                echo 'Building..'
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