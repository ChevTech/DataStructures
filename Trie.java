package DataStructures;

public class Trie {
    static class TrieVertex {
        int wordCount;
        int prefixCount;
        TrieVertex[] edges;

        public TrieVertex(){
            wordCount = 0;
            prefixCount = 0;
            edges = new TrieVertex[26];

            for(int i=0; i < edges.length; i++){
                edges[i] = null; // init to no edges
            }
        }
    }

    public void addWord(TrieVertex vertex, String word){
        if(word.length() == 0){
            vertex.wordCount += 1;
        } else {
            vertex.prefixCount += 1;
            char k = word.charAt(0);
            if(!exists(vertex.edges, k)){
                vertex.edges[k - 'a'] = new TrieVertex();
            }

            String nextPrefix = word.substring(1);
            addWord(vertex.edges[k - 'a'], nextPrefix);
        }
    }

    public int countWords(TrieVertex vertex, String word){
        if(word.length() == 0){
            return vertex.wordCount;
        }

        char k = word.charAt(0);

        if(!exists(vertex.edges, k)) {
            return 0;
        } else {
            String nextPrefix = word.substring(1);
            return countWords(vertex.edges[k - 'a'], nextPrefix);
        }
    }

    public int countWordsOffByOne(TrieVertex vertex, String word, Integer missingLetters){
        if(word.length() == 0){
            return vertex.wordCount;
        }

        char k = word.charAt(0);

        if(!exists(vertex.edges, k) && missingLetters == 0){
            return 0;
        } else if(!exists(vertex.edges, k)){
            String newPrefix = word.substring(1);
            return countWordsOffByOne(vertex, newPrefix, missingLetters-1);
        }

        String newPrix = word.substring(1);
        int r = countWordsOffByOne(vertex, newPrix, missingLetters-1);
        r += countWordsOffByOne(vertex.edges[k - 'a'], newPrix, missingLetters);
        return r;
    }

    public int countPrefixes(TrieVertex vertex, String word){
        if(word.length() == 0){
            return vertex.prefixCount;
        }

        char k = word.charAt(0);

        if(!exists(vertex.edges, k)){
            return 0;
        } else {
            String nextPrefix = word.substring(1);
            return countPrefixes(vertex.edges[k - 'a'], nextPrefix);
        }
    }

    private boolean exists(TrieVertex[] edges, char character){
        return edges[character - 'a'] != null;
    }
}
