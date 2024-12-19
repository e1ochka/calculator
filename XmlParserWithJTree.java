import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class XmlParserWithJTree extends JFrame {

    private JTree tree;

    public XmlParserWithJTree(String xmlFilePath) {
        setTitle("XML Parser");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Document document = parseXmlFile(xmlFilePath);
            DefaultMutableTreeNode root = buildTree(document.getDocumentElement());
            tree = new JTree(root);
            add(new JScrollPane(tree)); // Добавить JScrollPane для прокрутки, если дерево большое.
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ошибка при обработке XML файла: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        setVisible(true);
    }

    private Document parseXmlFile(String filePath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(filePath));
    }

    private DefaultMutableTreeNode buildTree(Element element) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(element.getNodeName());
        NodeList childNodes = element.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode instanceof Element) {
                node.add(buildTree((Element) childNode));
            } else if (childNode instanceof Text) { //Обработка текстовых узлов
                node.add(new DefaultMutableTreeNode(childNode.getNodeValue().trim()));
            }
            //пропускаем другие типы узлов
        }
        return node;
    }


    public static void main(String[] args) {
        //Создадим тестовый XML файл (можно заменить своим путем)
        String filePath = "test.xml";
        try {
            java.io.FileWriter fw = new java.io.FileWriter(filePath);
            fw.write("<root>\n<element1>Text1</element1>\n<element2>Text2</element2>\n<element3>\n<element4>Text4</element4>\n</element3>\n</root>");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        new XmlParserWithJTree(filePath);
    }
}

