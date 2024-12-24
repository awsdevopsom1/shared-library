def call () {
  node ('workstation1') {
     sh "find . | sed -e 'id' |xargs rm -rf"
     if(env.TAG_NAME ==~ ".*"){
        env.BranchName = env.TAG_NAME
     } else 
        env.BranchName = env.BRANCH_NAME
     stage('check out'){
        // git branch: 'main', url: 'https://github.com/awsdevopsom1/import-backend.git'
        checkout scmGit(branches: [[name: "${BranchName}"]],
         userRemoteConfigs: [[url: 'https://github.com/awsdevopsom1/import-backend.git']])
     }
 
     stage('compile'){}
    if(BRANCH_NAME == "main"){
     stage('code checkout'){
        git branch: 'main', url: 'https://github.com/awsdevopsom1/import-backend.git'
     }

    } else if(env.BRANCH_NAME ==~ "PR.*"){
       stage('Test cases') {}
       stage('integration Test cases') {} 
   } else if(env.TAG_NAME ==~ ".*"){
      stage('Build'){}
      stage('release')
     }
     
      else {
        stage('Test cases') {}
  }




    // stage('test cases'){}
    // stage('integration test cases')
   
    // stage('Release_App'){}
    }

  

}