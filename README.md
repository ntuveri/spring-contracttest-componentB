# spring-contracttest-componentB

Verify PACT files filtered by consumer componentA and tags tag1, tag2. Publish verification results with provider version 0.0.1.def123 and tag tagA.     
`gradlew.bat clean test -Dpact.verifier.publishResults=true -Dpact.provider.tag=tagA -Dpact.provider.version=0.0.1.def123 -Dpactbroker.consumers=componentA -Dpactbroker.consumerversionselectors.tags=tag1,tag2`