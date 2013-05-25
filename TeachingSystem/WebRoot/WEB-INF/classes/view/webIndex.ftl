<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<#escape x as x?html>
<#assign pageName = "webIndex" />
<#include "head.ftl">


<script type="text/javascript" src="/js/radar/amcharts.js"></script>

 <script type="text/javascript">
            var chart;

            var chartData = [{
                direction: "N",
                value: 8
            }, {
                direction: "NE",
                value: 9
            }, {
                direction: "E",
                value: 4.5
            }, {
                direction: "SE",
                value: 3.5
            }, {
                direction: "S",
                value: 9.2
            }, {
                direction: "SW",
                value: 8.4
            }, {
                direction: "W",
                value: 11.1
            }, {
                direction: "NW",
                value: 10
            }];


            AmCharts.ready(function () {
                // RADAR CHART
                chart = new AmCharts.AmRadarChart();
                chart.dataProvider = chartData;
                chart.categoryField = "direction";
                chart.startDuration = 1;

                // TITLE
                chart.addTitle("Prevailing winds", 15);

                // VALUE AXIS
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.gridType = "circles";
                valueAxis.fillAlpha = 0.05;
                valueAxis.fillColor = "#000000"
                valueAxis.axisAlpha = 0.2;
                valueAxis.gridAlpha = 0;
                valueAxis.fontWeight = "bold"
                valueAxis.minimum = 0;
                chart.addValueAxis(valueAxis);

                // GRAPH
                var graph = new AmCharts.AmGraph();
                graph.lineColor = "#FFCC00"
                graph.fillAlphas = 0.4;
                graph.bullet = "round"
                graph.valueField = "value"
                graph.balloonText = "[[category]]: [[value]] m/s"
                chart.addGraph(graph);

                // GUIDES
                // blue fill
                var guide = new AmCharts.Guide();
                guide.angle = 225;
                guide.tickLength = 0;
                guide.toAngle = 315;
                guide.value = 0;
                guide.toValue = 14;
                guide.fillColor = "#0066CC";
                guide.fillAlpha = 0.6
                valueAxis.addGuide(guide);

                // red fill
                var guide = new AmCharts.Guide();
                guide.angle = 45;
                guide.tickLength = 0;
                guide.toAngle = 135;
                guide.value = 0;
                guide.toValue = 14;
                guide.fillColor = "#CC3333";
                guide.fillAlpha = 0.6;
                valueAxis.addGuide(guide);

                // WRITE                
                chart.write("chartdiv");
            });
 </script>

<body style="background-color: rgb(243, 243, 243);">

<div id="chartdiv" style="width:200px; height:200px;"></div>

<div style="width: 50%; margin: 100px auto auto;">
    <h1 style="text-align: center; font-size: 60px;">登陆管理系统</h1>
    <#include "login.ftl">
</div>

    <form onsubmit="return uploadExcel();" class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="/teach/teacher/upload/addStudent/?classId=10000" id="uploadFrame">
        
         <input type="file" class="t file l " size="37" name="file" value="浏览">
         <input type="submit" value="上传成绩">
    </form>


</body>
</html>
</#escape>
<!--		
<#include "js.ftl">
-->