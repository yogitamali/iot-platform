exports.handler = (event, context, callback) => {
    var AWS = require('aws-sdk');
    var iotdata = new AWS.IotData({endpoint: 'a221a6r4ojicsi-ats.iot.ap-southeast-1.amazonaws.com'});
    var params = { payload:JSON.stringify({"state": {"desired": JSON.parse(event.desired) } }), thingName:event.thingName };
    try {
        iotdata.updateThingShadow(params, function(err, data) {
            if (err) callback(err, err.stack); // an error occurred
            else callback(null, data);         // successful response
        });
    } catch(t) {
        callback(null,t);
    }
};
