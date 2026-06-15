pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/SiengHeng/DevOpsFinal.git'
            }
        }

        stage('Build') {
            steps {
                dir('demo') {
                    sh 'chmod +x mvnw'
                    sh './mvnw clean install'
                }
            }
        }

        stage('Test') {
            steps {
                dir('demo') {
                    sh './mvnw test'
                }
            }
        }
    }
}