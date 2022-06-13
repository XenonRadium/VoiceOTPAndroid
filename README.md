# VoiceOTPAndroid

The project takes in a callee number and proceeds to send a call to the phone number and provides a random 6 digit number (placeholder for OTP)
to the phone number via a programmable voice.

The application uses Twilio API to provide programmable voice functionality and call functionality.


## Server
The project uses Node.js code to instantiate a serverless communication via ngrok between the client and the callee. The node.js file is contained
in the Server directory within the project.

Twilio CLI commands is required to create a deploy the Twilio serverless application whenever any changes is made to the Javascript file.

The command for this is: twilio serverless:deploy

