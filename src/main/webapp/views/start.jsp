<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>圣诞大作战</title>
    <meta name="renderer" content="webkit">
    <script type="text/javascript" src='../static/js/libs/jquery-3.2.1.min.js'></script>
    <script src="../static/js/libs/flexible.js"></script>
    <link rel="stylesheet" type="text/css" href="../static/style/style.css">
    <link rel="stylesheet" type="text/css" href="../static/style/init.css">
</head>

<body>
    <div class="main">
        <ul class="voiceList">
            <li class="voiceItem voice">
                <img class='voi' src="../static/images/voice.png" onclick="change(this)">
            </li>
            <li class="voiceItem help">
                <img src="../static/images/help.png">
            </li>
        </ul>
        <div class="logo">
            <img src="../static/images/logo.png">
        </div>
        <div class="btns">
            <div class="start">
                <a href="index.jsp">
                    <img src="../static/images/start.png">
                </a>
            </div>
            <div class="rank">
                <a href="rank.jsp">
                    <img src="../static/images/rank.png">
                </a>
            </div>
        </div>
    </div>
    <div class="kaifa">
        <div class="help-info">
            <img src="../static/images/info.png">
            <div class="close">
                <img src="../static/images/close.png">
            </div>
        </div>
    </div>
    <audio id="bgmusic" autoplay="autoplay">
        <source src="../static/images/music.mp3" type="audio/mp3">
    </audio>
    <script type="text/javascript" src='../static/js/http.js'></script>
    <script type="text/javascript" src='../static/js/req.js'></script>
    <script type="text/javascript">
    
    var audio = document.getElementById("bgmusic");
    var src1 = 'http://wx.yyeke.com/christmasgame/static/images/voice.png';
    var src2 = 'http://wx.yyeke.com/christmasgame/static/images/off.png';
    $('.help').on('click', function() {
        $(".help-info").css("display", "block");
        $(".kaifa").addClass('game-over');
        $(".kaifa").css("zIndex", "10000");
    })
    $('.close').on('click', function() {
        $('.help-info').css("display", 'none');
        $(".kaifa").removeClass('game-over');
        $(".kaifa").css("zIndex", "-1")
    })
    function change(img) {
        var s = img.src;
        console.log(s);
        if(s == src1){
            img.src = src2;
            audio.pause();
        }
        if(s == src2){
            img.src = src1
            audio.play();
        }
    }
    </script>
    <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
    <script src="https://wx.idsbllp.cn/wx-api/share.js "></script>
    <script>
    WXSHARE.config({ debug: false });
    WXSHARE.ready(function() {
        var option = {
            title: "圣诞大作战 | @亲们：今年圣诞哪里过？有福利！快来这里嗨翻天",
            link: "http://wx.yyeke.com/christmasgame",
            imgUrl: 'http://wx.yyeke.com/christmasgame/static/images/back.jpg',
            desc: '小伙伴快来参加圣诞活动啊～～～',
            type: '',
            success: function() {
                share();
                console.log('分享成功');
            },
            cancel: function() {
                console.log('取消分享');
            },
        };
        wx.onMenuShareTimeline(option);
        wx.onMenuShareAppMessage(option);
        wx.onMenuShareQQ(option);
        wx.onMenuShareWeibo(option);
        wx.onMenuShareQZone(option);
    });
    </script>
</body>

</html>