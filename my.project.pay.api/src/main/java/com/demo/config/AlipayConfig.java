package com.demo.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016101500693330";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCNcMaAESyEFNKIAUQEjoBIHHM+Jh++GdCONBsRYwB/BaRX+O/dEF8jPIolYPSFp8POj1hSFLP90aXn2sPAtkYMYSgBaDGU9B3e6Azot/LSiuJrRqGWRhWYEUi/fYzQ75ue+qxQ6k42IYfPSDHb2PaHC9ZgGZTzEgBZI5iSz1JQdCC3xLbGN1jrSP5TjzDLKThOdFxew5bhQnn7SpX6EXdGuK9rsIVJM3vC/OEbZvz9kxFY62M1OqK4EtBoikkuFExjQ+Aib5+ywT9muPTemb+WkXX/Lb/UMxtHrq0xcQf85u6+iofljvS5tnlM3ckQpvAP8CujYEF7nZFPrCgWE5KPAgMBAAECggEAF6fh+y9kI58b6xVTG/P9bpIbkCxuKKxGnlIGqOzG+3elGfWPoIiJcOpCLKg/nU8Gx7hfzDKcO/phZhrHnKafpphlSX70s/X0PQDQj+ecHCyQW+LPScPk/q79XNGRyKwaegdw5IObmyAWh8uKUzvBV6raKu58Y/8BwebbMMqt+LiByz5OVwcZW6z8gPwFpBFaGFp1kYITahlJtExVcAiGjqKLAiq+maSrskuGLdpaxGS+Cy4TmgdoWjwbmn6elq9dORO5HR9EPO7D19sWAcNSv8ARjPjTW/l9vdbrnFwr9isyjUSIXt1UfDampv8a6VzC/bc1r/Il3HlL7i1yUqnOoQKBgQD0ey6yusIH7h1v2qiowGrpRDwa3MxzjmGpmL91ZqE4EmDPnKva/NP+7EXcrbEXn36tkpdtp/QLedLef/cXhLKowEgepaZCW+gs5BNMjvpw1f9xkga7uFMKbTKp0qLIsRyroYjFEjzjm2sZ9Sg1SZ2AOhFkSrMuDPVYf3GMoy7fsQKBgQCUGsPNaIsNJXlHkMfrypX+SIrvsapqt7AHZZomqcRoDK7nwZ4vxCJDzilg1mydfCxLrHJ9bpAg4rtn9ObIEG1XrJV0QRq0GbP9LBfYizHOD7/5VOCRah6qUJ/mbSCvTR4ddA0M2m16ZHOGEwa9aNQJsMbmYdvQmPlBlYEbG4pmPwKBgAiCVRDBcUlnXzzqZl5hPBdcWZ8xuif3d6y4dUl3xfq2oQn40P5xOEuyG4hfvz8sPPN7m6ukYdkYZ195Ird91d4Luzbd2xiKLF2LnqRWgZ71w8tHItbbxoAhZtcETsgfafkLSUBaryXY3fRsVPbt1f6Fht9vvibHKq7m2KPldhcxAoGBAJE8nf71IkdGFGTF6gekpuqyIO1lgQUeFRVr4XtAyEZxCaRkDsgwwplTtugr3l+hwZ6EAdJXdieJsNYFJ7vRwChWKknS/mjXO9ZuQwQrnC/C6jOLtJ1lP6UtY2EIayD9syaXA6UzHmED2PYF+noMLsNCBAsws+sf2G8kOXNn0pTpAoGAB4TQ8f612rar6ZXCdcrKjeM45WnT8mCBzWGGitf6QfYNoOYjL2Fu88SP0DfhghAsFYcTmxzoF6W5C4SYXX3zmBHIiZsmDnIaSv0ktALcvZId/iLMvkxMz0tP9Xuf7byO0AyvhgObFxXePJK267x81P5Mzcvo5ClSCwgTU1VcIaY=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://abl.free-http.svipss.top/mynotifyurl";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:10002/myreturnurl";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkXZVPXrwOmg1BqJSQDLyJUgXDYDH1KBrd2TwDEXaBbl1TwmVVZd8Cfp34o4NASdxvljne8sb0QdLde09WL+x3tZ9BkC2rPm8t3/EUqsVXMdt/qK7aepH9IrVBiyeHwnXIjCK2htnNAnlYgths3uDqhxZ/nM0G+qdfAyPkxTdMsNbHzxZ9enEUrXVrjikzNK3Tl+ZJHg5lfr9DABCWoNFEZIZY0D81hHuuPXYxsBDf9oyXkytLl5yEFYApllDgU/d3bjsIOnoDDoW8CoRMTK2Ht+ybtwaz0qptfHE4MDG+7mC7pYfd7kktoypvvFe25qhBKnT1AkBDihNE0IYe2LLuQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
