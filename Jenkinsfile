node("master") {
  deleteDir()
  checkout scm

  gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
  // short SHA, possibly better for chat notifications, etc.
  shortCommit = gitCommit.take(6)

  echo "Commit: ${gitCommit}";

  echo "My branch is: ${env.BRANCH_NAME}"

  stage('Env') {
    sh 'set'
  }

  stage('Build site') {
    
     
    sh 'cd my-app && ./gradlew build'

    stash includes: '**/build/libs/*.jar', name: 'app' 

    archiveArtifacts artifacts: "my-app/build/libs/*.jar", fingerprint: false

    junit '*/build/test-results/test/*.xml' 
    
    publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'my-app/build/reports/pmd', reportFiles: 'main.html', reportName: 'HTML Report'])
/*    publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'my-app/build/reports/pmd', reportFiles: 'test.html', reportName: 'HTML Report'])
*/
  }

/*  stage("Confirm") {
    input id: 'ID', message: 'Continue?', ok: 'Yes'
  }
*/

  stage("Parallel tests") {
    // a loop counter of 131072 didn't yet show the error
    def parallels =  [
      '0': { node('master') {
        stage("Nested") {
          unstash 'app'
          println "Execute 0"
          sh 'pwd'
          sh 'find .'
          sh 'sleep 60'
        }
       }},
       '1': { node('master') {
        println "Execute 1"
        sh 'pwd'
        sh 'find .'
        sh 'sleep 60'
       }},
       '2': { node('master') {
        println "Execute 2"
        sh 'sleep 60'
       }} ,
       '3': { node('master') {
        println "Execute 3"
        sh 'sleep 60'
    }}
    ]

    parallel parallels
  }
}
 /*
  node("DockerNode") {
     env.PATH = "/usr/local/bin:${env.PATH}"

     stage('Inside Docker') {
       timeout(160) {
         img = docker.image("openjdk:8")

         img.inside() {
           checkout scm

           sh 'hostname'
           sh 'ls -l my-app'
           sh 'cd my-app && ./gradlew build'
         }
       }
     }
  }
*/
