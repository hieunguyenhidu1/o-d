const  cloudinary  = require('cloudinary').v2;
          
cloudinary.config({ 
  cloud_name: 'dpcf32qva', 
  api_key: '885173316828978', 
  api_secret: 'qWNufZLGE6Swu0PgGsC6tzsk1Pg' 
});

module.exports = cloudinary