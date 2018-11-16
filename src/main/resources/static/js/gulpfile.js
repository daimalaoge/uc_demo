const url = require('url');
const fs = require('fs');
const path = require('path');
const del = require('del');
const pkg = require('./package.json');

const gulp = require('gulp');
const less = require('gulp-less');
const gulpif = require('gulp-if');
const rename = require('gulp-rename');
const header = require('gulp-header');
const uglify = require('gulp-uglify');
const sourcemaps = require('gulp-sourcemaps');
const babel = require('gulp-babel');
const cleanCSS = require('gulp-clean-css');
const htmlreplace = require('gulp-html-replace');
const livereload = require('gulp-livereload');
const webserver = require('gulp-webserver');
const zip = require('gulp-zip');

const headerTpl = '/** kitadmin-v${version} ${license} License By ${homepage} Author ${author} */\n';
const headerData = pkg;

//清理 dist文件夹
gulp.task('clear', function(cb) {
  return del(['./dist/*'], cb);
});
//zip压缩
gulp.task('zip', () =>
  gulp.src('./dist/**/*.*')
  .pipe(zip(`kitadmin-v${pkg.version}.zip`))
  .pipe(gulp.dest('./release'))
);
//web服务器 用于开发环境
gulp.task('webserver', function() {
  gulp.src('./src/') // 服务器目录（./代表根目录）
    .pipe(webserver({ // 运行gulp-webserver
      port: 8000, //端口，默认8000
      livereload: true, // 启用LiveReload
      open: true, // 服务器启动时自动打开网页
      directoryListing: {
        enable: true,
        path: 'index.html' //配置默认访问页面
      },
      middleware: function(req, res, next) {
        //mock local data
        const urlObj = url.parse(req.url, true),
          method = req.method;
        if (!urlObj.pathname.match(/^\/api/)) { //不是api开头的数据，直接next
          next();
          return;
        }
        const mockDataFile = path.join(__dirname, urlObj.pathname) + ".js";
        //file exist or not
        fs.access(mockDataFile, fs.F_OK, function(err) {
          if (err) {
            res.setHeader('Content-Type', 'application/json');
            res.end(JSON.stringify({
              "status": "没有找到此文件",
              "notFound": mockDataFile
            }));
            return;
          }
          const data = fs.readFileSync(mockDataFile, 'utf-8');
          res.setHeader('Content-Type', 'application/json');
          res.end(data);
        });
        next();
      },
      proxies: []
    }));
});
// 编译less
gulp.task('less', function() {
  return gulp.src(['./src/css/*.less', './src/css/**/*.less'])
    .pipe(gulpif(['!kitadmin.less'], less()))
    .pipe(less())
    .pipe(gulp.dest('./src/css/'));
});
// 打包layui
gulp.task('layui', function() {
  return gulp.src(['./src/lib/layui/**/*.*'])
    .pipe(gulp.dest('./dist'));
});
// 打包js
gulp.task('js', function(cb) {
  // 打包其他依赖的js
  gulp.src('./src/lib/*.js')
    .pipe(gulp.dest('./dist'));

  // 打包自定义的js模块
  return gulp.src(['./src/js/*.js'])
    .pipe(sourcemaps.init())
    .pipe(babel({
      presets: ['env']
    }))
    .pipe(uglify())
    .pipe(header(headerTpl + ';', headerData))
    .pipe(sourcemaps.write('.'))
    .pipe(gulp.dest('./dist/lay/kit_modules'));
});
// 打包css ,打包之前先编译less
gulp.task('css', ['less'], function() {
  return gulp.src(['./src/css/**/*.css'])
    .pipe(cleanCSS({ compatibility: 'ie8' }))
    .pipe(header(headerTpl, headerData))
    .pipe(gulp.dest('./dist/css'));
});
// 打包html
gulp.task('html', function() {
  gulp.src('./src/mockjs/*.js')
    .pipe(gulp.dest('./dist'));

  gulp.src('./src/views/*/**')
    .pipe(gulp.dest('./dist/views'));

  gulp.src('./src/docs/*.html')
    .pipe(gulp.dest('./dist/docs'));
  gulp.src('./src/temp/*.html')
    .pipe(gulp.dest('./dist/temp'));

  return gulp.src('./src/index.html')
    .pipe(htmlreplace({
      js: ['polyfill.min.js', 'layui.js', 'kitadmin.js', 'mockjs-config.js'],
      css: {
        src: [
          ['css/layui.css', 'layui'],
          ['css/theme/default.css', 'theme'],
          ['css/kitadmin.css', 'kitadmin']
        ],
        tpl: '<link rel="stylesheet" href="%s" id="%s">'
      },
      use: '<script>layui.use("admin");</script>'
    }))
    .pipe(gulp.dest('./dist'));
});
// 运行打包任务
gulp.task('build', ['layui', 'js', 'css', 'html', 'zip'], function() {
  process.exit();
});
// 监听less文件的变更进步热编译
const watcher = gulp.watch(['./src/css/*.less', './src/css/**/*.less'], ['less']);
watcher.on('change', function(event) {
  console.log('LESS ' + event.path + ' was ' + event.type + ', running tasks.');
});
// 启动本地服务器
gulp.task('dev', ['less', 'webserver']);
// 默认任务
gulp.task('default', function() {
  console.log('default.');
});