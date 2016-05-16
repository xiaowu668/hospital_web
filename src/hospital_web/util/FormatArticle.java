package hospital_web.util;
public class FormatArticle {
	public static String format(String articleContent){
		articleContent=formatCode(articleContent);
		return articleContent;
	}
	private static String formatCode(String articleContent){
		articleContent=articleContent.replaceAll("<", "&lt;");
		articleContent=articleContent.replaceAll(">", "&gt;");
		if(articleContent.indexOf("[code=Java]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Java\\]", "<textarea wrap='virtual' name='code' class='java'>");
		}
		if(articleContent.indexOf("[code=C]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=C\\]", "<textarea wrap='virtual' name='code' class='c'>");
		}
		if(articleContent.indexOf("[code=C#]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=C#\\]", "<textarea wrap='virtual' name='code' class='c#'>");
		}
		if(articleContent.indexOf("[code=Css]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Css\\]", "<textarea wrap='virtual' name='code' class='css'>");
		}
		if(articleContent.indexOf("[code=Delphi]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Delphi\\]", "<textarea wrap='virtual' name='code' class='delphi'>");
		}
		if(articleContent.indexOf("[code=Jscript]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Jscript\\]", "<textarea wrap='virtual' name='code' class='jscript'>");
		}
		if(articleContent.indexOf("[code=Php]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Php\\]", "<textarea wrap='virtual' name='code' class='php'>");
		}
		if(articleContent.indexOf("[code=Python]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Python\\]", "<textarea wrap='virtual' name='code' class='python'>");
		}
		if(articleContent.indexOf("[code=Ruby]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Ruby\\]", "<textarea wrap='virtual' name='code' class='ruby'>");
		}
		if(articleContent.indexOf("[code=Sql]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Sql\\]", "<textarea wrap='virtual' name='code' class='sql'>");
		}
		if(articleContent.indexOf("[code=Vb]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Vb\\]", "<textarea wrap='virtual' name='code' class='vb'>");
		}
		if(articleContent.indexOf("[code=Xml]")!=-1){
			articleContent=articleContent.replaceAll("\\[code=Xml\\]", "<textarea wrap='virtual' name='code' class='xml'>");
		}
		articleContent=articleContent.replaceAll("\\[/code\\]", "</textarea>");
		articleContent=articleContent.replaceAll("\\[", "<");
		articleContent=articleContent.replaceAll("\\]", ">");
		return articleContent;
	}
}
