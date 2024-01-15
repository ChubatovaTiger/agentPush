import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.11"

project {

    buildType(Build2)

    features {
        amazonEC2CloudImage {
            id = "PROJECT_EXT_2"
            profileId = "amazon-1"
            agentPoolId = "-2"
            name = "chubatovaPush"
            vpcSubnetId = "subnet-043178c302cabfe37"
            instanceType = "t3.medium"
            securityGroups = listOf("sg-072d8bfa0626ea2a6")
            source = Source("ami-0b80008bcbc184132")
        }
        amazonEC2CloudImage {
            id = "PROJECT_EXT_3"
            profileId = "amazon-1"
            agentPoolId = "-2"
            name = "amiim"
            vpcSubnetId = "subnet-043178c302cabfe37"
            instanceType = "t3.medium"
            securityGroups = listOf("sg-072d8bfa0626ea2a6")
            source = Source("ami-0819c29c24aaed21b")
        }
        amazonEC2CloudProfile {
            id = "amazon-1"
            name = "ec2profile"
            serverURL = "http://10.128.93.78"
            terminateIdleMinutes = 30
            region = AmazonEC2CloudProfile.Regions.EU_WEST_DUBLIN
            authType = accessKey {
                keyId = "credentialsJSON:ef0cd595-fa41-4e28-8169-24caa83b3fbe"
                secretKey = "credentialsJSON:b8b3646f-5cbd-4934-b840-62f0b0bd3b18"
            }
            param("agentPushPreset", "1705306954297")
        }
    }
}

object Build2 : BuildType({
    name = "build2"
})
