function callback(){result='';if (xml.readyState==4 && xml.status==200){var responseText = xml.responseText;console.log(responseText);}}
var result='';var xml = new XMLHttpRequest();xml.open('POST', 'http://tieba.baidu.com/f/commit/post/delete', true);xml.onreadystatechange = callback; xml.setRequestHeader('Content-type','application/x-www-form-urlencoded'); xml.send('commit_fr=pb&ie=utf-8&tbs=d316085121f93c631441951687&kw=%E5%93%88%E5%B0%94%E6%BB%A8%E5%B7%A5%E4%B8%9A%E5%A4%A7%E5%AD%A6&fid=35522&tid=4033906702&is_vipdel=0&pid=75571153471&is_finf=false');

//Available in chrome. Work very well. Now, I need