package ro.ubb.geekstack.iterators;

import ro.ubb.geekstack.models.Post;

import java.util.List;

public class PostIterator implements IIterator<Post> {

    private List<Post> elements;
    private Integer index;
    private Boolean isFinished;

    public PostIterator(List<Post> elements) {
        index = 0;
        isFinished = false;
        this.elements = elements;
    }

    @Override
    public Post getCurrent() {
        try {
            return elements.get(index);
        }
        catch (IndexOutOfBoundsException e) {
            isFinished = true;
            return null;
        }
    }

    @Override
    public void next() {
        this.index += 1;
    }

    @Override
    public Boolean isFinished() {
        return isFinished || elements.size() == index;
    }
}
