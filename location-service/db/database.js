const mongoose = require('mongoose');
const dbString = `mongodb://mongodb:27017/locations`;//mognodb
mongoose.connect(dbString,
    {
        useCreateIndex:true
    },(err)=>{
    if(!err){
        console.log('Database Connected Successfully');
    }else{
        console.log('Database Failed to connect');
    }
});

module.exports = mongoose;