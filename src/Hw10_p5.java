import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

class RelationNode {
    private String mName;
    private ArrayList<String> mFollows = new ArrayList<>();

    public RelationNode() {}

    public RelationNode(final String szName) {
        this.mName = szName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ArrayList<String> getmFollows() {
        return mFollows;
    }

    public void setmFollows(ArrayList<String> mFollows) {
        this.mFollows = mFollows;
    }
}

public class Hw10_p5 {
    private static RelationNode findNode(final String szPerson, final ArrayList<RelationNode> adjList) {
        for (RelationNode node : adjList) {
            if (szPerson.equals(node.getmName())) {
                return node;
            }
        }

        return null;
    }

    private static ArrayList<String> directlyFollows(final String szPerson, final ArrayList<RelationNode> adjList) {
        RelationNode node = findNode(szPerson, adjList);
        if (null == node) {
            return null;
        }

        return node.getmFollows();
    }

    private static ArrayList<String> indirectlyFollows(final String szPerson, final ArrayList<RelationNode> adjList) {
        ArrayList<String> lstDirectlyFollows = directlyFollows(szPerson, adjList);
        if (null == lstDirectlyFollows) {
            return null;
        }

        Set<String> setDirectFollows = new HashSet<>();
        Queue<String> lstQueue = new LinkedList<>();
        for (String szItem : lstDirectlyFollows) {
            setDirectFollows.add(szItem);
            lstQueue.offer(szItem);
        }

        Set<String> setIndirectFollows = new HashSet<>();
        while (lstQueue.size() > 0) {
            String szItem = lstQueue.poll();
            ArrayList<String> lstTemp = directlyFollows(szItem, adjList);
            if (null == lstTemp) {
                continue;
            }

            for (String szIndirect : lstTemp) {
                if (false == szIndirect.equals(szPerson)
                        && false == setDirectFollows.contains(szIndirect)) {
                    setIndirectFollows.add(szIndirect);
                    lstQueue.offer(szIndirect);
                } else {
                    continue;
                }
            }
        }

        if (setIndirectFollows.size() <= 0) {
            return null;
        }

        ArrayList<String> lstRet = new ArrayList<>();
        for (String szItem : setIndirectFollows) {
            lstRet.add(szItem);
        }

        return lstRet;
    }

    public static void allFollows(final String szPerson, final ArrayList<RelationNode> adjList) {

        ArrayList<String> lstDirectlyFollows = directlyFollows(szPerson, adjList);
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%s directly follows {", szPerson));
        if (lstDirectlyFollows != null) {
            for (int i = 0; i < lstDirectlyFollows.size(); ++i) {
                if (0 == i) {
                    sb.append(lstDirectlyFollows.get(i));
                } else {
                    sb.append("," + lstDirectlyFollows.get(i));
                }
            }
        }
        sb.append("} ");


        ArrayList<String> lstIndirectlyFollows = indirectlyFollows(szPerson, adjList);
        sb.append(String.format("%s directly indirectly {", szPerson));
        if (lstIndirectlyFollows != null) {
            for (int i = 0; i < lstIndirectlyFollows.size(); ++i) {
                if (0 == i) {
                    sb.append(lstIndirectlyFollows.get(i));
                } else {
                    sb.append("," + lstIndirectlyFollows.get(i));
                }
            }
        }
        sb.append("} ");

        System.out.println(sb.toString());
    }

    public static ArrayList<RelationNode> init(final String szFilePath) {
        FileReader fr = null;
        BufferedReader bf = null;
        ArrayList<RelationNode> adjList = new ArrayList<>();

        try {
            fr = new FileReader(szFilePath);
            bf = new BufferedReader(fr);

            String str;
            while ((str = bf.readLine()) != null) {
                str = str.trim();
                if (str.length() <= 0) {
                    continue;
                }

                String[] lstData = str.split(",");
                RelationNode node = null;
                int nPos = 0;
                for (String szItem : lstData) {
                    if (0 == nPos) {
                        node = new RelationNode(szItem.trim());
                    } else {
                        node.getmFollows().add(szItem.trim());
                    }
                    nPos++;
                }

                adjList.add(node);
            }

            return adjList;
        } catch (Exception ex) {
            return null;
        } finally {
            try {
                bf.close();
                fr.close();
            } catch (Exception ex) {

            }
        }
    }

    public static void main(String[] args) {
        ArrayList<RelationNode> adjList = init("/Users/wangxiaofeng/Github-Thinkman/Assignment/src/follows_input.txt");
        for (RelationNode node : adjList) {
            allFollows(node.getmName(), adjList);
        }
    }
}
