<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 优先使用webkit内核渲染 -->
    <meta name="renderer" content="webkit">

    <script type="text/javascript" src='../static/js/libs/jquery-3.2.1.min.js'></script>
    <script src="../static/js/libs/flexible.js"></script>
    <script type="text/javascript">
        var img = new Image();
        img.src = '../static/images/thief.png'
        var img1 = new Image();
        img1.src = '../static/images/fastthief.png'
    </script>
    <link rel="stylesheet" type="text/css" href="../static/style/games-xmas.css">
    <link rel="stylesheet" type="text/css" href="../static/style/init.css">
</head>

<body>
    <div class="game-container" id='game-container'>
        <div class="settings">
            <div class="time">
                <img src="../static/images/time.png">
                <div class="seconds">
                </div>
            </div>
            <ul class="voiceList">
                <li class="voiceItem voice">
                    <img src="../static/images/goldvoice.png" onclick="change(this)">
                </li>
                <li class="voiceItem help">
                    <img src="../static/images/goldhelp.png">
                </li>
            </ul>
        </div>
    </div>
    <div class="over">
        <img src="../static/images/over.png">
        <span class="place">
            <span class="placebefore">
            恭喜，你排名第
            </span>
            <span class="p">
            </span>
            <span class="placeafter">
            名
            </span>
        </span>
        <span class="point">
            <span class="pointbefore">
                坚持时间
            </span>
            <span class="poi">

            </span>
            <span class="pointafter">
                秒
            </span>
        </span>
        <span class="chance">
            <span class="chancebefore">
                你还剩下
            </span>
            <span class="cha">
            </span>
            <span class="chanceafter">
                次机会哦
            </span>
        </span>

        <ul class="btnsList">
            <li class="btn viewrank">
                <a href="rank.jsp">
                    <img src="../static/images/viewranka.png">
                </a>
            </li>
            <li class="btn again">
            <a href="start.jsp">
                <img src="../static/images/againa.png">
            </a>
            </li>
        </ul>
    </div>
    <audio id="bgmusic" autoplay="autoplay">
        <source src="../static/images/music.mp3" type="audio/mp3">
    </audio>



    <script type="text/javascript" src='../static/js/libs/lodash.js'></script>
    <script type="text/javascript" src='../static/js/http.js'></script>
    <script type="text/javascript" src='../static/js/req.js'></script>
    <script type="text/javascript" src='../static/js/games-xmas.js'></script>
    <script type="text/javascript">
    document.addEventListener('touchmove', function(e){e.preventDefault()}, false);
    // 开始游戏
    $.xMas('#game-container');
    var audio = document.getElementById("bgmusic");
    var iw = innerWidth / parseFloat($('html').css("fontSize"));
    var ih = innerHeight / parseFloat($('html').css("fontSize"));
    var src1 = 'http://wx.yyeke.com/christmasgame/static/images/goldvoice.png';
    var src2 = 'http://wx.yyeke.com/christmasgame/static/images/goldoff.png';
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
            link: "",
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