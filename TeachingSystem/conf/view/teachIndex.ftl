<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "teachIndex" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{font-size:83%;}

</style>
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
<body style="height:1000px;">
        <form onsubmit="return uploadExcel();" class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="/teach/teacher/upload/addStudent/?classId=10000" id="uploadFrame">
        
         <input type="file" class="t file l " size="37" name="file" value="浏览">
         <input type="submit" value="上传成绩">
    </form>
      ${levelName!''}
      <#if level = 0>
      
      <#elseif level=1>
      <#elseif level=2>
      <#elseif level=3>
      </#if>
</body>
</html>
</#escape>
		
<#include "js.ftl">

<script>


</script>