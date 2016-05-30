#!/usr/bin/env node

// Copy webclient files
// Automatically copies files from the webclient module to this module
// before the `compile` command. This allows sharing code between the two modules
// and enables using KotlinJS

var fs = require('fs');
var rimraf = require('rimraf'); //same as rm -rf
var path = require('path');
var replace = require('replace');
var ncp = require('ncp').ncp;
ncp.limit = 16;

function copy(srcPath, destPath, callback) {
  console.log('Copying files from ' + srcPath + ' to ' + destPath + '...');
  ncp(srcPath, destPath, function (err) {
    if (err) {
      return console.error(err);
    }
    console.log('Copying files complete.');
    callback();
  });
}


rimraf(path.join('www','*'), function() {
  console.log('Deleting "www" directory complete.');

  copy(path.join('..', 'webclient', 'src', 'main', 'web'), 'www', function() {
    console.log('Creating "www/js" directory.');
    try {
      fs.mkdirSync(path.join('www', 'js'), function (err) {
        if (err) {
          console.error(err);
        } else {
          console.log('Creating "www/js" directory complete.');
        }
      });
    } catch(ex) {}
    console.log('Creating "www/js" directory complete.');
    copy(path.join('www', 'gen', 'webclient.js'), path.join('www', 'js', 'app.js'), function() {
      copy(path.join('www', 'gen', 'webclient.meta.js'), path.join('www', 'js', 'app.meta.js'), function() {
        console.log('Doing CORDOVA replacements:');
        replace({
            regex: "<!--CORDOVA (.*?)-->",
            replacement: "$1",
            paths: ['www/index.html'],
            recursive: false,
            silent: false,
        });
        console.log('Doing CORDOVA replacements complete.');
        console.log('Doing app.js replacements:');
        replace({
            regex: "gen/webclient.",
            replacement: "js/app.",
            paths: ['www/index.html'],
            recursive: false,
            silent: false,
        });
        console.log('Doing app.js replacements complete.');
      });
    });
  });
});
