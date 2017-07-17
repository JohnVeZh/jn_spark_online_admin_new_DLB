/**
 * @(#)$CurrentFile
 * 版权声明 山东益信通科贸有限公司，版权所有 违者必究
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> Company：山东益信通科贸有限公司
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */
/*
 * 日志工具类
 */


package com.easecom.common.util;

import org.apache.log4j.*;
//import java.util.logging.*;
import java.util.Date;
import java.net.*;

public class LogUtils {
//  private Logger logger = null;
//  private LogUtils(String classname) {
//    logger = Logger.getLogger(classname);
//    makeLogger(logger);
//  }
//  private LogUtils(Class cls){
//  logger = Logger.getLogger(cls.getName());
//  makeLogger(logger);
//  }
//  public static LogUtils getLogUtils(String name) {
//    return new LogUtils(name);
//  }
//
//  public static LogUtils getLogUtils(Class cls) {
//    return new LogUtils(cls);
//  }
//  
//  public void fine(String msg){
//   logger.fine(msg);
//  }
//  public void info(String msg) {
//    logger.info(msg);
//  }
//  public void config(String msg){
//    logger.config(msg);
//  }
//  public void severe(String msg){
//    logger.severe(msg);
//  }
//  public void warning(String msg){
//    logger.warning(msg);
//  }
//  public void warning(String msg, Throwable t){
//    logger.log(Level.WARNING, msg, t);
//  }
//  private static void makeLogger(Logger log1) throws SecurityException {
//    try {
//      //创建记录处理器
//      Handler mh = new ConsoleHandler();
//      int limit = 1000000;
//      Handler fh = new FileHandler("d:/my%g.log", limit, 3);
//   //为记录处理器设置Formatter
//      mh.setFormatter(new MyFormatter());
//         //为记录器添加记录处理器
//      log1.addHandler(mh);
//      log1.addHandler(fh);
//     //禁止消息处理将日志消息上传给父级处理器
//      log1.setUseParentHandlers(false);
//      System.out.println("----------------");
//
//    }
//    catch (Exception ex) {
//
//    }
//  }
//
//}
//
//class MyFormatter
//    extends Formatter {
//  public String format(LogRecord rec) {
//    StringBuffer buf = new StringBuffer(1000);
//    buf.append(new Date().toLocaleString()); //时间
//    buf.append(' ');
//    buf.append(rec.getLevel()); //消息级别
//    buf.append(' ');
//    buf.append(rec.getLoggerName());
//    buf.append(' ');
//    buf.append(formatMessage(rec)); //格式化日志记录数据
//    buf.append('\n'); //换行
//    return buf.toString();
//  }

  private Logger logger;
  private static org.apache.log4j.Logger rootLogger = LogManager.getRootLogger();
  /**
   * log4j的属性文件
   */
  private static final String configFile = "log4j.properties";

  static {
    _init();
  }

  /**
   * 初始化日志配置
   */
  private static void _init() {
    /*查找制定的log4j.properties配置文件*/
    URL url = Thread.currentThread().getContextClassLoader().getResource(
        configFile);
    if (url != null) { //获取到了log4j.properties配置文件的url路径
      //System.out.println("url isn't null");
      PropertyConfigurator.configureAndWatch(configFile, 1000);
    }
    else {
   //   System.out.println("url is null");
      Appender app = new ConsoleAppender(new PatternLayout(PatternLayout.
          TTCC_CONVERSION_PATTERN));
      app.setName("console");
      rootLogger.addAppender(app);
    }
    rootLogger.info(LogUtils.class.getName() + " initialized.");
  }

  /**
   *
   * @param className String
   */
  private LogUtils(String className) {
    logger = Logger.getLogger(className);
  }

  private LogUtils(Class cls){
    logger = Logger.getLogger(cls);
  }

  public static LogUtils getLogger(String className) {
    return new LogUtils(className);
  }

  public static LogUtils getLogger(Class cls){
    return new LogUtils(cls);
  }

  public void loggerReset(){
    LogManager.resetConfiguration();
    _init();
  }

  public void debug(Object message) {
    logger.log(Level.DEBUG,message);
  }

  public void debug(Object message, Throwable t) {
    logger.log(Level.DEBUG,message,t);
  }

  public void info(Object message) {
    logger.log(Level.INFO,message);
  }

  public void info(Object message, Throwable t) {
    logger.log(Level.INFO,message,t);
  }

  public void warn(Object message) {
    logger.log(Level.WARN,message);
  }

  public void warn(Object message, Throwable t) {
    logger.log(Level.WARN,message,t);
  }

  public void error(Object message) {
    logger.log(Level.ERROR,message);
  }

  public void error(Object message, Throwable t) {
    logger.log(Level.ERROR,message,t);
  }

  public void fatal(Object message) {
    logger.log(Level.FATAL,message);
  }

  public void fatal(Object message, Throwable t) {
    logger.log(Level.FATAL,message,t);
  }

}
