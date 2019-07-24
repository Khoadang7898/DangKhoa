var critical = require('critical');

critical.generate({
    base: 'templates/SearchTool/travelix/',
    src: 'offers.html',
    dest: 'offers.css',
});