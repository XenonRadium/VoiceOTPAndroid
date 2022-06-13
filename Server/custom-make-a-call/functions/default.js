const callerNumber = '1234567890';
const callerId = 'client:alice';

exports.handler = function(context, event, callback) {
    const twiml = new Twilio.twiml.VoiceResponse();

    const to = event.To;

    twiml.say('Please insert a callee number.');

    callback(null, twiml);
}