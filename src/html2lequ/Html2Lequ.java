package html2lequ;

import breadTrip2FunJoy.bean.TravelContentBean;
import org.apache.http.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Html2Lequ implements PageProcessor {

    public static final String DIR = "3007066";

    public static final String URL = "http://bbs.huway.com/thread/" + DIR + "/1/1.html";

    public static final String LOCAL_PATH = "/Users/huway_iosdev2/Desktop/TravelTransporter" + "/";

    private Site site = Site.me().setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");


    private List<TravelContentBean> contentBeanList = new ArrayList<>();


    public static void main(String[] args) {
        Spider.create(new Html2Lequ()).addUrl(URL).addPipeline(new JsonFilePipeline("./")).run();
    }

    @Override
    public void process(Page page) {
        try {
            File root = new File(LOCAL_PATH);
            if (!root.exists()) {
                root.mkdir();
            }

            File dir = new File(LOCAL_PATH + DIR);
            if (!dir.exists()) {
                dir.mkdir();
            }

            String fromDir = LOCAL_PATH + "tool/.*";
            String toDir = LOCAL_PATH + DIR;

            Runtime.getRuntime().exec(new String[]{"cp", "-c", fromDir + " " + toDir});

            // 出发日期
            Date startDate = new Date();
            String dateStart = "";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            String dateStartStr;
            if (page.getHtml().css("div.forumListHeader").css("span").all().size() > 0) {
                dateStartStr = page.getHtml().css("div.forumListHeader").css("span").all().get(1);
                Document docDate = Jsoup.parse(dateStartStr);
                dateStart = docDate.getElementsByTag("span").html();
                startDate = df.parse(dateStart); // 出发的第一天
            }

//            dateWriter.write(0 + ":" + 1 + ":" + dateStart + ":" + startDate.getTime() / 1000 + "\n");
            long startDateTime = startDate.getTime() / 1000;
            // 大图
            String bannderImage = page.getHtml().css("p").css("img").all().get(0);
            Document docBannerImage = Jsoup.parse(bannderImage);
            String bannerImage = docBannerImage.getElementsByTag("img").attr("src");

            // 大标题
            String topTitleHtml = page.getHtml().css("h2").all().get(0);
            Document docTitle = Jsoup.parse(topTitleHtml);
            String topTitle = docTitle.body().getElementsByTag("h2").html();

            // 标题信息写到文件
//            titleWriter.write(topTitle + "\n");
//            titleWriter.write("wget " + bannerImage + " -O head.jpeg \n");
//            titleWriter.write(dateStart + "\n");

            // 标题信息写到文件
//            wgetWriter.write("wget " + bannerImage + " -O head.jpeg \n");

            // 每一个分页内容
            List<String> allContent = page.getHtml().css("p").all();

            int w = 960, h = 540;

            for (int i = 0; i < allContent.size(); i++) {
                String strText = allContent.get(i);
                Document p = Jsoup.parse(strText);
                String content = p.body().getElementsByTag("span").html();
                String imgUrl = p.body().getElementsByAttribute("src").attr("src");

                if (content.startsWith("<img src=") || content.startsWith("<br />") || content.equals("&nbsp;")) {
                    content = "";
                }
                if (TextUtils.isEmpty(content) && TextUtils.isEmpty(imgUrl)) continue;


                if (contentBeanList.size() > 0 && (contentBeanList.get(contentBeanList.size() - 1).getContent().equals(content) ||
                        contentBeanList.get(contentBeanList.size() - 1).getImages().equals(imgUrl))) continue;

                if ((TextUtils.isEmpty(content) && !TextUtils.isEmpty(imgUrl))

                        || (!TextUtils.isEmpty(content) && TextUtils.isEmpty(imgUrl))

                        && (i + 1 < allContent.size())) {

                    String strTextNext = allContent.get(i + 1);
                    Document pNext = Jsoup.parse(strTextNext);
                    String contentNext = pNext.body().getElementsByTag("span").html();
                    String imgUrlNext = pNext.body().getElementsByAttribute("src").attr("src");

                    if (contentNext.startsWith("<img src=") || contentNext.startsWith("<br />") || contentNext.equals("&nbsp;")) {
                        contentNext = "";
                    }

                    if (!TextUtils.isEmpty(contentNext) && TextUtils.isEmpty(content)) {
                        content = contentNext;
                    }

                    if (!TextUtils.isEmpty(imgUrlNext) && TextUtils.isEmpty(imgUrl)) {
                        imgUrl = imgUrlNext;
                    }
                }

                TravelContentBean travelContentBean = new TravelContentBean();
                travelContentBean.setTravels_id("");
                travelContentBean.setContent(content);
                travelContentBean.setImages(imgUrl);
                travelContentBean.setDate(startDateTime);
                travelContentBean.setImage_width(w);
                travelContentBean.setImage_height(h);
                contentBeanList.add(travelContentBean);

            }

            contentBeanList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Site getSite() {
        return site;
    }

}
