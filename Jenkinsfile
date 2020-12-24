def pacticipant = 'componentB'
def pacticipantVersion
def integrationEnvTag = 'develop'
def tags = ['develop', 'preprod', 'prod']

pipeline {
    agent { any }

    stages {
        stage('Setup') {
            steps {
                echo 'downloading pact standalone tools'
                sh "curl -LO https://github.com/pact-foundation/pact-ruby-standalone/releases/download/v1.88.26/pact-1.88.26-linux-x86_64.tar.gz"
                sh "tar xzf pact-1.88.26-linux-x86_64.tar.gz"

                script {
                    // If in a feature branch insert the tag first
                    if (!tags.contains(env.BRANCH_NAME)) {
                        tags.add(0, env.BRANCH_NAME)
                    }
                    echo 'tags: ' + tags

                    // TODO: retrieve component version dynamically reading VERSION file (or by other means)
                    def commit = sh(
                            script: "printf \$(git rev-parse --short HEAD)",
                            returnStdout: true
                    )
                    pacticipantVersion = '0.0.1.' + commit
                }
            }
        }

        stage('Verify contracts') {
            script {

                def consumerTags = tags.join(',')
                echo 'consumerTags: ' + consumerTags

                // TODO: override application.properties settings with -Dpactbroker.host=pact-broker, ...
                sh "./gradlew clean test -Dpact.verifier.publishResults=true -Dpact.provider.tag=${env.BRANCH_NAME} -Dpact.provider.version=${pacticipantVersion} -Dpactbroker.consumerversionselectors.tags=${consumerTags} -Dpactbroker.host=pact-broker"
            }
        }

        stage('Can I deploy?') {
            sh "./pact/bin/pact-broker can-i-deploy --broker-base-url http://pact-broker:9292 --broker-username pact --broker-password password --pacticipant ${pacticipant} --version ${pacticipantVersion} --to ${integrationEnvTag} --retry-interval 30 --retry-while-unknown 10"
        }
    }
}

