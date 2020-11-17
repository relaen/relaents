let str = "select a.area,b.* , count(a.*) from CA a,CB b  left join CC c on b.id=c.id    where a.agent= b.agent and   a.id+b.id= 3 order by a.id asc,b.id desc";
let arr = str.split(/[\s\,=\,]/g);
console.log(arr);
let s = 'xxx.y=';
console.log(s.split('='));
