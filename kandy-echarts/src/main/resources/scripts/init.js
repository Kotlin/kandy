new Promise(function (resolve, reject) {
    var script = document.createElement("script");
    script.onload = resolve;
    script.onerror = reject;
    script.src = "https://assets.pyecharts.org/assets/v5/echarts.min.js";
    document.head.appendChild(script);
}).then(() => {

});
