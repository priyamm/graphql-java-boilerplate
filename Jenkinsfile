node {
    withMaven(maven:'maven') {
        stage('Checkout') {
            git url: 'https://github.com/priyamm/graphql-java-boilerplate.git', credentialsId: 'priyamm', branch: 'dev'
        }
        stage('Build') {
            sh 'mvn clean install'
            def pom = readMavenPom file:'pom.xml'
            print pom.version
            env.version = pom.version
        }
        stage('Image') {
            dir ('discovery-service') {
                def app = docker.build "https://hub.docker.com/r/priyamm/graphql"
                app.push()
            }
        }
        stage ('Run') {
            docker.image("https://hub.docker.com/r/priyamm/graphql/").run('-p 8761:8761')
        }      
    }
}