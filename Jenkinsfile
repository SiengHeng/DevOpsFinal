pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/SiengHeng/DevOpsFinal.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean install'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
    }

    post {
        failure {
            mail to: 'srengty@gmail.com',
            subject: "Build Failed",
            body: "Check Jenkins logs"
        }
    }
}
