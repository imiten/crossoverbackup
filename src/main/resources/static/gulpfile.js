var gulp = require('gulp')
var connect = require('gulp-connect')
var liveReload = require('gulp-liveReload')

gulp.task('connect', function () {
	connect.server({
		root: '',
		port: 4000
	})
})


gulp.task('watch', function() {
  var server = liveReload({start:true});
  gulp.watch(['./app/**'], ['reload']);
});

gulp.task('reload', function() {
  liveReload();
});


gulp.task('default', ['watch', 'connect']);
