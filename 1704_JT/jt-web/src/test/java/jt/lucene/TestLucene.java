package jt.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class TestLucene {
	@Test
	public void test() throws Exception{
		//创建文档对象
		Document document = new Document();
		document.add(new LongField("id", 1474391949, Store.YES));
		document.add(new TextField("title", "不朽凡人传",Store.YES));
		document.add(new TextField("sellPoint", "爽文",Store.YES));
		document.add(new DoubleField("price", 1888,Store.YES));
		document.add(new IntField("num", 9999,Store.YES));
		document.add(new StringField("image", "http://image.jt.com/images/2017/07/27/14/08//17.jpg",Store.YES));
		//创建标准分词器
		Analyzer analyzer = new StandardAnalyzer();
		//创建配置对象
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);
		//创建一个目录，将创建的索引文件存放入当前工程的index目录
		Directory dir = FSDirectory.open(new File("./index"));
		IndexWriter indexWriter = new IndexWriter(dir, config);
		indexWriter.addDocument(document);
		indexWriter.close();
		dir.close();
	}
	@Test
	public void test01() throws Exception{
		Directory dir = FSDirectory.open(new File("./index"));
		IndexSearcher  searcher = new IndexSearcher(IndexReader.open(dir));
		Query query = new TermQuery(new Term("title","不"));	//查询表达式
		TopDocs topDocs = searcher.search(query, 10);
		System.out.println("点击："+topDocs.totalHits);
		for(ScoreDoc scoreDoc:topDocs.scoreDocs){
			System.out.println("得分："+scoreDoc.score);
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.println("编号："+doc.get("id"));
			System.out.println("标题："+doc.get("title"));
			System.out.println("卖点："+doc.get("sellPoint"));
			System.out.println("价格："+doc.get("price"));
			System.out.println("数量："+doc.get("num"));
		}
		dir.close();
	}
}
