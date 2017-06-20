import gulp from 'gulp';
import gulpif from 'gulp-if';
import concat from 'gulp-concat';
import webpack from 'webpack';
import gulpWebpack from 'webpack-stream';
import named from 'vinyl-named';
import livereload from 'gulp-livereload';
import plumber from 'gulp-plumber';
import rename from 'gulp-rename';
import uglify from 'gulp-uglify';
import {log,colors} from 'gulp-util';
import args from './util/args';

gulp.task('scripts',()=>{
    return gulp.src(['app/js/index.js'])//打开文件
        .pipe(plumber({//统一处理gulp的pipe错误处理逻辑
            errorHandle:function () {
                
            }
        }))
        .pipe(named())//对文件命名
        .pipe(gulpWebpack({//webpack编译设置
            module:{
                loaders:[{
                    test:/\.js$/,
                    loader:'babel'
                }]
            }
        }),null,(err,stats)=>{//webpack错误处理
            log(`Finished ${colors.cyan('scripts')}`,stats.toString({
                chunks:false
            }))
        })
        .pipe(gulp.dest('server/public/js'))//将编译好的文件存储
        .pipe(rename({//新复制一份.min.js
            basename:'cp',
            extname:'.min.js'
        }))
        .pipe(uglify({//对复制的minjs进行压缩
            compress:{
                properties:false
            },
            output:{
                'quote_keys':true
            }
        }))
        .pipe(gulp.dest('server/public/js'))//将压缩的js文件存储
        .pipe(gulpif(args.watch,livereload()))//如果传入的命令参数watch为true，那么热更新
})