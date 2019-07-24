var critical = require('critical');

critical.generate({
    inline: true,
    base: 'templates/Admin/startbootstrap-sb-admin-2-gh-pages/',
    src: 'register.html',
    dest: 'register-critical.html',
});