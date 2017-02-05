package ua.dnigma.fragment2fragment_sendobject.tasks;

import android.os.AsyncTask;
import android.provider.DocumentsContract;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ua.dnigma.fragment2fragment_sendobject.callbacks.OnDataLoadedCallback;
import ua.dnigma.fragment2fragment_sendobject.callbacks.OnDataProgressUpdate;
import ua.dnigma.fragment2fragment_sendobject.model.MovieModel;
import ua.dnigma.fragment2fragment_sendobject.ui.activity.MainActivity;

import static android.view.View.X;

/**
 * Created by Даниил on 29.12.2016.
 */

public class ImaxRequestTask extends AsyncTask<String, Integer, List<MovieModel>> {

    private OnDataLoadedCallback onDataLoadedCallback;
    private OnDataProgressUpdate onDataProgressUpdate;

    public ImaxRequestTask(OnDataLoadedCallback onDataLoadedCallback) {
        this.onDataLoadedCallback = onDataLoadedCallback;

    };

    public ImaxRequestTask(OnDataLoadedCallback onDataLoadedCallback, OnDataProgressUpdate onDataProgressUpdate) {
        this.onDataLoadedCallback = onDataLoadedCallback;
        this.onDataProgressUpdate = onDataProgressUpdate;

    };



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onDataLoadedCallback != null) {
            onDataLoadedCallback.beforeTaskStart();
        }
    }

    @Override
    protected List<MovieModel> doInBackground(String... strings) {
        List<MovieModel> result = null;
        HttpGet httpGet = new HttpGet(strings[0]);
        HttpClient httpClient = new DefaultHttpClient();
//        try {
////            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int codeStatus = httpResponse.getStatusLine().getStatusCode();
            if (codeStatus == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                String data = EntityUtils.toString(httpEntity);
                result = parse(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    protected void onPostExecute(List<MovieModel> movieModels) {
        super.onPostExecute(movieModels);
        if (onDataLoadedCallback != null) {
            onDataLoadedCallback.afterTaskEnd();
            if (movieModels != null) {
                onDataLoadedCallback.onSucsses(movieModels);
            } else {
                onDataLoadedCallback.onFail("List is null");
            }

        }
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (onDataProgressUpdate != null) {
            onDataProgressUpdate.onProgressUpdate(values[0]);
        }
    }

    private List<MovieModel> parse(String data) {
        List<MovieModel> movieModels = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder buider = factory.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(data));
            Document document = buider.parse(source);

            NodeList nodeList = document.getElementsByTagName("movie");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int id = Integer.parseInt(element.getAttribute("id"));
                    String url = element.getAttribute("url");
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String dtStart = element.getElementsByTagName("dt-start").item(0).getTextContent();
                    String dtEnd = element.getElementsByTagName("dt-end").item(0).getTextContent();

                    MovieModel movieModel = new MovieModel();
                    movieModel.setId(id);
                    movieModel.setUrl(url);
                    movieModel.setTitlle(title);
                    movieModel.setDataStart(dtStart);
                    movieModel.setDataEnd(dtEnd);

                    movieModels.add(movieModel);

                    publishProgress(++i);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieModels;
    }

    public void setOnDataProgressUpdate(OnDataProgressUpdate onDataProgressUpdate) {
        this.onDataProgressUpdate = onDataProgressUpdate;
    }
}