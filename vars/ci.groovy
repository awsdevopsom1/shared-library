def call () {
  node ('workstation1') {
    sh 'env'
    stage('compile'){}
    stage('test cases'){}
    stage('integration test cases')
    stage('Build'){}
    stage('Release_App'){}
    }

}  

