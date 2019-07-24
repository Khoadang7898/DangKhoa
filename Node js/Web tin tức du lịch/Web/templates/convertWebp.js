const imagemin = require('imagemin');
const imageminWebp = require('imagemin-webp');

imagemin(['./templates/img_convert/*.{jpg,png,webp}'], './templates/img_convert', {
    use: [
        imageminWebp({quality: 50})
    ]
}).then(() => {
    console.log('Images optimized');
});