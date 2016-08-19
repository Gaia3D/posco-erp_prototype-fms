<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.StringReader" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.Charset" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="com.itextpdf.text.Document" %>
<%@ page import="com.itextpdf.text.PageSize" %>
<%@ page import="com.itextpdf.text.pdf.PdfWriter" %>

<%@ page import="com.itextpdf.tool.xml.XMLWorker" %>
<%@ page import="com.itextpdf.tool.xml.XMLWorkerFontProvider" %>
<%@ page import="com.itextpdf.tool.xml.XMLWorkerHelper" %>
<%@ page import="com.itextpdf.tool.xml.css.CssFile" %>
<%@ page import="com.itextpdf.tool.xml.css.StyleAttrCSSResolver" %>
<%@ page import="com.itextpdf.tool.xml.html.CssAppliers" %>
<%@ page import="com.itextpdf.tool.xml.html.CssAppliersImpl" %>
<%@ page import="com.itextpdf.tool.xml.html.Tags" %>
<%@ page import="com.itextpdf.tool.xml.parser.XMLParser" %>
<%@ page import="com.itextpdf.tool.xml.pipeline.css.CSSResolver" %>
<%@ page import="com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline" %>
<%@ page import="com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline" %>
<%@ page import="com.itextpdf.tool.xml.pipeline.html.HtmlPipeline" %>
<%@ page import="com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext" %>


<%

	String htmls = request.getParameter("html");
	System.out.println(htmls);
	out.clear();
	out=pageContext.pushBody();

	// step 0
	//파일 다운로드 설정
	response.setContentType("application/pdf");
	String fileName = URLEncoder.encode("한글파일명", "UTF-8"); // 파일명이 한글일 땐 인코딩 필요
	response.setHeader("Content-Transper-Encoding", "binary");
	response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");

// css, font 파일 위치
	String cssPath = "D:/git/posco-erp_prototype-fms/poscoFMS/src/main/webapp/common/css/style.css";
	//String fontPath = "C:/WebContent/resource/font/MALGUN.TTF";
	
	// step 1
	Document document = new Document(PageSize.A4, 50, 50, 50, 50);

	// step 2
    PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
//	writer.setViewerPreferences(PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage);	// 이게 뭐지??
    writer.setInitialLeading(12.5f);
	   
	   
	// step 3
	document.open();
	
	// step 4
	// CSS
	CSSResolver cssResolver = new StyleAttrCSSResolver();
	CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(cssPath));
	cssResolver.addCss(cssFile);

	// HTML, 폰트 설정
	XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	//fontProvider.register(fontPath, "MalgunGothic"); // MalgunGothic은 alias,
	CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

	HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

	// Pipelines
	PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
	HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	 
	XMLWorker worker = new XMLWorker(css, true);
	XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
	
	String htmlStr = "<html><head><body>"
	            + htmls
	        	+ "</body></head></html>";
	 
	//StringReader strReader = new StringReader(htmlStr);
	StringReader strReader = new StringReader(htmls);
	//xmlParser.parse(strReader);
	
	document.newPage();
	
	strReader = new StringReader(htmlStr);
	//xmlParser.parse(strReader);
	
	// step 5
	document.close();
	writer.close();
%>