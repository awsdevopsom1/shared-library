def call () {
pipeline {
    agent {
        node { label 'workstation1'}
    }
    stages {
        stage('compile'){
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
        stage('Release App'){
            steps{
                echo 'compile'
            }
        }
    }
}  

}