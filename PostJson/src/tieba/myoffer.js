function callback(){result='';if (xml.readyState==4 && xml.status==200){var responseText = xml.responseText;console.log(responseText);}}
var result='';var xml = new XMLHttpRequest();xml.open('POST', 'http://www.myoffer.cn/external/api/courses', true);xml.onreadystatechange = callback; xml.setRequestHeader('Content-type','application/json; charset=utf-8'); xml.send('{"target":"course","action":"add","value":{"university":"saos","course":{}}}');

//j_head_focus_btn
//Available in chrome. Work very well. Now, I need