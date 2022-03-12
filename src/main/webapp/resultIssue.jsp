<%-- 
    Document   : resultIssue
    Created on : 5 Nov, 2020, 3:40:02 PM
    Author     : Mohak Chavan
--%>

<%@page import="org.jfree.chart.ui.RectangleInsets"%>
<%@page import="org.jfree.chart.ui.RectangleEdge"%>
<%@page import="java.awt.Font"%>
<%@page import="org.jfree.chart.renderer.category.StandardBarPainter"%>
<%@page import="org.jfree.chart.renderer.category.BarRenderer"%>
<%@page import="java.awt.geom.Rectangle2D"%>
<%@page import="java.awt.Shape"%>
<%@page import="java.awt.BasicStroke"%>
<%@page import="java.awt.Stroke"%>
<%@page import="org.jfree.chart.ChartColor"%>
<%@page import="java.io.IOException"%>
<%@page import="org.jfree.chart.ChartUtils"%>
<%@page import="java.awt.Color"%>
<%@page import="org.jfree.chart.ui.Align"%>
<%@page import="java.io.File"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="org.jfree.chart.plot.PlotOrientation"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.Issues"%>
<%@page import="java.util.List"%>
<%@page import="java.util.concurrent.CountDownLatch"%>
<%@page import="com.google.firebase.database.DatabaseError"%>
<%@page import="com.google.firebase.database.DataSnapshot"%>
<%@page import="com.google.firebase.database.ValueEventListener"%>
<%@page import="com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.IssuesHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script>
	    .dashboardImages{
	    justify - content: center;
	    display: flex;
	    float: left;
	    width: 100 % ;
	    /* margin: 0% 2%; */
	    align - items: center;
	    flex - wrap: wrap;
	    }

	    .no - float - items{
	    align - items: center;
	    width: 100 % ;
	    justify - content: center;
	    display: flex;
	    }

	    .dashCard{
	    width: 10 % ;
	    border - radius: 5px;
	    background - color: #008577;
	    background - image: linear - gradient(315deg, #00574B, #008577);
	    box - shadow: 1px 2px 9px 1px grey;
	    margin: 1 % ;
	    border: 2px solid var (--green);
	    text - transform: uppercase;
	    }

	    .bold - white{
	    font - weight: bold;
	    color: white;
	    }

	    .cardFooter{
	    font - size: larger;
	    }

	    .cardHeader{
	    font - size: xx - large;
	    margin: 10 % ;
	    }
	</script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1><br/>
        <%
	    /*IssuesHelper helper = new IssuesHelper();
	    final CountDownLatch latch = new CountDownLatch(1);
	    final StringBuilder sb = new StringBuilder();
	    helper.getIssueReference().orderByKey().startAt("3310").endAt("3320")
		    .addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
			    for (DataSnapshot snap : snapshot.getChildren()) {
				if (snap.getKey() != null && !snap.getKey().isEmpty() && !snap.getKey().contentEquals(Constants.FIREBASE.DATABASE.TOTAL_RECORDS)) {
				    Issues issue = snap.getValue(Issues.class);
				    sb.append("<br/>"
					    + " Issue ID: " + String.valueOf(issue.getIssueNo()));
				}
			    }
			    latch.countDown();
			}

			@Override
			public void onCancelled(DatabaseError error) {
			    sb.append(" Error Details: " + error.getDetails() + " Error Message: " + error.getMessage());
			    latch.countDown();
			}
		    });
	    latch.await();
	    out.println(sb.toString());*/
        %>


	<%
 /*DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    dataset.addValue(53, "A", "Mar 2020");
	    dataset.addValue(45, "A", "Feb 2020");
	    try {
		JFreeChart chart = ChartFactory.createBarChart("Monthly Javak", "", "", dataset, PlotOrientation.VERTICAL, true, true, false);

		chart.setBackgroundImage(ImageIO.read(new File(getServletContext().getRealPath("/images/dashboard_background.png"))));
		chart.setBackgroundImageAlignment(Align.FIT_HORIZONTAL);
		chart.setBackgroundImageAlignment(Align.FIT_VERTICAL);
		chart.setBackgroundImageAlpha(0.85f);
		chart.getTitle().setPaint(Color.WHITE);
		chart.getTitle().setPosition(RectangleEdge.BOTTOM);
		chart.getLegend().setVisible(false);
		chart.setPadding(new RectangleInsets(20, 20, 20, 20));
		chart.getPlot().setBackgroundAlpha(0);

		chart.getPlot().setOutlineVisible(false);
		chart.setBorderVisible(false);
		chart.getCategoryPlot().setRangeGridlinePaint(Color.BLACK);
//		chart.getCategoryPlot().setRangeGridlineStroke(new BasicStroke(1));
		chart.getCategoryPlot().setDomainGridlinePaint(Color.BLACK);
		chart.getCategoryPlot().setDomainGridlinesVisible(true);
//		chart.getCategoryPlot().setDomainGridlineStroke(new BasicStroke(1));

		chart.getCategoryPlot().getRangeAxis().setAxisLineStroke(new BasicStroke(2));
		chart.getCategoryPlot().getRangeAxis().setAxisLinePaint(Color.BLACK);
		Font font = chart.getCategoryPlot().getRangeAxis().getTickLabelFont();
		chart.getCategoryPlot().getRangeAxis().setTickLabelFont(new Font(font.getName(), Font.PLAIN, 18));
		chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.BLACK);

		chart.getCategoryPlot().getDomainAxis().setAxisLineStroke(new BasicStroke(2));
		chart.getCategoryPlot().getDomainAxis().setAxisLinePaint(Color.BLACK);
		font = chart.getCategoryPlot().getDomainAxis().getTickLabelFont();
		chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font(font.getName(), Font.PLAIN, 18));
		chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.BLACK);

		((BarRenderer) chart.getCategoryPlot().getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) chart.getCategoryPlot().getRenderer()).setMaximumBarWidth(0.10);
		chart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.WHITE);

		File demoFile = new File(getServletContext().getRealPath("/charts/demoChart.jpeg"));

		if (!demoFile.exists()) {
		    demoFile.createNewFile();
		} else {
		    demoFile.delete();
		    demoFile.createNewFile();
		}
		ChartUtils.saveChartAsJPEG(demoFile, chart, 640, 480);
	    } catch (IOException ex) {

	    }
	     */
	%>

	<!-- <div id="dashboardImages" class="dashboardImages">

	    <div id="dashboardCard" class="dashCard" style="float: left;" align="center">
		<p class="bold-white cardHeader" align="center">88</p>
		<p class="bold-white cardFooter" style="margin: 20% 10% 2% 10%;" align="center">September</p>
		<p class="bold-white cardFooter" style="margin: 0% 10% 5% 10%;" align="center">Aavak</p>
	    </div>

	    <div id="dashboardCard" class="dashCard" style="float: left;" align="center">
		<p class="bold-white cardHeader" align="center">88</p>
		<p class="bold-white cardFooter" style="margin: 20% 10% 2% 10%;" align="center">September</p>
		<p class="bold-white cardFooter" style="margin: 0% 10% 5% 10%;" align="center">Aavak</p>
	    </div>

	    <div class="no-float-items">
	<%--	<img src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/> --%>
		<div id="dashboardCard" class="dashCard" style="float: left;" align="center">
		    <p class="bold-white cardHeader" align="center">88</p>
		    <p class="bold-white cardFooter" style="margin: 20% 10% 2% 10%;" align="center">September</p>
		    <p class="bold-white cardFooter" style="margin: 0% 10% 5% 10%;" align="center">Aavak</p>
		</div>
	    </div>
	</div> -->

	<%	    String text = request.getParameter("name1");
	    if (text != null) {
		out.println("<h1>" + text + "</h1>");
	    }
	%>

	<form method="get" action="resultIssue.jsp">
	    <input type="checkbox" name="name1" id="name1"/>
	    <input type="submit" value="submit"/>
	</form>
    </body>
</html>
