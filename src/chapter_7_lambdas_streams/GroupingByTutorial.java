package chapter_7_lambdas_streams;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

enum BlogPostType {
    NEWS,
    REVIEW,
    GUIDE
}

class BlogPost {
    String title;
    String author;
    BlogPostType type;
    int likes;

    public int getLikes() {
        return likes;
    }

    public String getTitle() {
        return title;
    }

    // Java 16 introduces record to generate immutable Java classes
    record AuthPostTypesLikes(String author, BlogPostType type, int likes) {};
    record PostCountTitlesLikesStats(long postCount, String titles, IntSummaryStatistics likeStats) {};

    public BlogPost(String title, String author, BlogPostType type, int i) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.likes = i;
    }

    BlogPostType getType(){return type;}
    String getAuthor(){return author;}
}

class Tuple {
    BlogPostType type;
    String author;

    public Tuple(BlogPostType type, String author) {
    }
}


class Pair<K, V> {
    public Pair(K type, V author) {
    }
    // stub for javafx.util or org.apache.commons.lang3.tuple
}


// source: https://www.baeldung.com/java-groupingby-collector
public class GroupingByTutorial {
    public static void main(String[] args) {
        List<BlogPost> posts = Arrays.asList(
                new BlogPost("title", "author", BlogPostType.NEWS, 4)
        );

        // simple grouping by a single col
        // group blog posts by their type:
        Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType));

        // groupingBy with a Complex Map key type
        // the key of the resulting map could be any object as long as we make sure
        // equals() and hashcode() are impl'd

        // group blog posts in the list by type and author...
        Map<Pair<BlogPostType, String>, List<BlogPost>> postPerTypeAndAuthor =
                posts.stream()
                        .collect(
                                groupingBy(post -> new Pair<>(post.getType(), post.getAuthor()))
                        );

        // using Tuple class
        Map<Tuple, List<BlogPost>> postsPerTypeAndAuthor = posts.stream()
                .collect(
                        groupingBy(post -> new Tuple(post.getType(), post.getAuthor()))
                );

        // using record
        Map<BlogPost.AuthPostTypesLikes, List<BlogPost>> postsPer
                = posts.stream().collect(groupingBy(
                        post -> new BlogPost.AuthPostTypesLikes(
                                post.getAuthor(),
                                post.getType(),
                                post.getLikes()
                        )
        ));

        // Modifying the Return Map value type
        // 2nd collector / downstream collector applied to results of first
        Map<BlogPostType, Set<BlogPost>> postsPerSetType = posts.stream()
                .collect(groupingBy(BlogPost::getType, toSet()));

        // Grouping my multiple fields with downstream collector
        // to group the posts first by author then by type
        Map<String, Map<BlogPostType, List<BlogPost>>> map = posts.stream()
                .collect(groupingBy(
                        BlogPost::getAuthor, groupingBy(BlogPost::getType)
                ));

        // Getting the average from grouped results
        // find the ave likes for each blog post type
        Map<BlogPostType, Double> aveLikesPerType = posts.stream()
                .collect(
                        groupingBy(BlogPost::getType, averagingInt(BlogPost::getLikes))
                );

        // Getting the Sum from Grouped Results
        Map<BlogPostType, Integer> likesPerType = posts.stream()
                .collect(groupingBy(
                        BlogPost::getType, summingInt(BlogPost::getLikes)
                ));

        // Getting max/min from grouped results
        Map<BlogPostType, Optional<BlogPost>> maxLikesPerPostType =
                posts.stream().collect(groupingBy(
                        BlogPost::getType, maxBy(comparingInt(BlogPost::getLikes))
                ));

        // Getting summary for grouped results
        Map<BlogPostType, IntSummaryStatistics> likeStatsPerType =
                posts.stream().collect(groupingBy(
                       BlogPost::getType, summarizingInt(BlogPost::getLikes)
                ));

        // Aggregating multiple attributes of a grouped result

        // Mapping grouped results to a different type
        // get a concat of titles of the posts for each post type
        Map<BlogPostType, String> postsPerType2 = posts.stream()
                .collect(groupingBy(
                        BlogPost::getType,
                        mapping(BlogPost::getTitle, joining(", ", "Post titles: [", "]"))
                ));

        // Modifying the return Map type
        EnumMap<BlogPostType, List<BlogPost>> postsPerType3 = posts.stream()
                .collect(groupingBy(
                        BlogPost::getType,
                        () -> new EnumMap<>(BlogPostType.class),
                        toList()
                ));
    }

    public void aggMultAttrsOfGroupedResult() {
        List<BlogPost> posts = Arrays.asList(
                new BlogPost("title", "author", BlogPostType.NEWS, 4),
                new BlogPost("title2", "author2", BlogPostType.REVIEW, 4)
        );
        Map<String, BlogPost.PostCountTitlesLikesStats> postsPerAuthor =
                posts.stream().collect(groupingBy(
                        BlogPost::getAuthor,
                        collectingAndThen(
                                toList(),
                                list -> {
                                    long count = list.stream().map(BlogPost::getTitle).count();
                                    String titles = list.stream().map(BlogPost::getTitle)
                                            .collect(joining(" : "));
                                    IntSummaryStatistics summary = list.stream()
                                            .collect(summarizingInt(BlogPost::getLikes));
                                    return new BlogPost.PostCountTitlesLikesStats(count, titles, summary);
                                }
                        )
                ));
    }
}

