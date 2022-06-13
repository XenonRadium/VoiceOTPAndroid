exports.handler = function (context, event, callback) {
    const twilioClient = context.getTwilioClient();


    const from = '+19377452535';
//    const to = '+60163477019';
    const to = event.To;

    var char1, char2, char3, char4, char5, char6;
    var generatedOTPNumber = event.OTP;
    var convertedOTP = generatedOTPNumber.toString();
    char1 = convertNumToString(convertedOTP[0]);
    char2 = convertNumToString(convertedOTP[1]);
    char3 = convertNumToString(convertedOTP[2]);
    char4 = convertNumToString(convertedOTP[3]);
    char5 = convertNumToString(convertedOTP[4]);
    char6 = convertNumToString(convertedOTP[5]);

    const twiml = '<Response><Say>Hello, your OTP Number is, ' + char1 + " ," + char2 + " ," + char3 + " ," + char4 + " ," + char5 + " ," + char6 + '</Say></Response>'
    //const twiml = '<Response><Say>Hello, This is Bernard. Give me all your money.</Say></Response>'

    twilioClient.calls
        .create({to, from, twiml})
        .then((call) => {
            console.log('Call successfully placed');
            console.log(call.sid);

            return callback(null, `Success! Call SID: ${call.sid}`);
        })
        .catch((error) => {
            console.error(error);
            return callback(error);
        })
}


function convertNumToString(number){
    if (number == "1") {
        return "one";
    }else if (number == "2")
    {   return "two";}
    else if (number == "3")
    {   return "three";}
    else if (number == "4")
    {   return "four";}
    else if (number == "5")
    {   return "five";}
    else if (number == "6")
    {   return "six";}
    else if (number == "7")
    {   return "seven";}
    else if (number == "8")
    {   return "eight";}
    else if (number == "9")
    {   return "nine";}
    else if (number == "0")
    {   return "zero";}
    return "";
}