def call () {
pipeline {
    agent {
        node { label 'workstation1'}
    }
    stages {
        stage('main'){
            steps{
                echo 'compile'
            }
        }
        stage('test cases'){
            steps{
                echo 'compile'
            }
        }
        stage('Build'){
            steps{
                echo 'compile'
            }
        }
        stage('Release_App'){
            steps{
                echo 'compile'
            }
        }
    }

}  

}



/////