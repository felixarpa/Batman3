package view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.CategoryResultPresenter;

import java.util.ArrayList;

public class CategoryResultView extends ListResult {

    private ArrayList<Label> relevance;

    public CategoryResultView(CategoryResultPresenter presenter){
        super(presenter);
        this.presenter = presenter;

        initializePanes();
        initializeViews();
        //setListeners();
        completePanes();
        topBarPane.setCenter(contentVBox);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        relevance = new ArrayList<>(Config.LISTS_SIZE);

        Font font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 18);

        for (int i = 0; i < 10; ++i) {
            relevance.add(new Label());
            relevance.get(i).setMinSize(100, 20);
            relevance.get(i).setMaxSize(100, 24);
            relevance.get(i).setFont(font);
            relevance.get(i).setTextFill(Paint.valueOf("white"));
        }
    }

    @Override
    public void setContent(int index, String node) {
        int i = index++ % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);
        relevance.get(i).setText(elements[2]);

        if (elements[0].equals("")) numbers.get(i).setText("");
        else numbers.get(i).setText(index + "");
    }

    @Override
    protected void completePanes() {
        buildLine();
        buildPanes();
    }

    protected void buildLine() {
        int i = 0;
        for (HBox line : results) {
            line.getChildren().addAll(
                    numbers.get(i),
                    names.get(i),
                    ids.get(i),
                    relevance.get(i)
            );
            ++i;
        }
    }

    public void askSimilarOp() {

    }
}
