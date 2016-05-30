#!/usr/bin/env node

// Copy webclient files
// Automatically copies files from the webclient module to this module
// before the `compile` command. This allows sharing code between the two modules
// and enables using KotlinJS

var path = require('path');
var ncp = require('ncp').ncp;
ncp.limit = 16;

function copy(srcPath, destPath) {
  console.log('Copying files from ' + srcPath + ' to ' + destPath + '...');
  ncp(srcPath, destPath, function (err) {
    if (err) {
      return console.error(err);
    }
    console.log('Copying files complete.');
  });
}

copy(path.join('..', 'out', 'production', 'webclient'), 'www')
copy(path.join('..', 'webclient', 'src', 'main', 'web'), 'www')
