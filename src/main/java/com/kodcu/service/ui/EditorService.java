package com.kodcu.service.ui;

import com.kodcu.component.EditorPane;
import com.kodcu.component.LabelBuilt;
import com.kodcu.component.MenuItemBuilt;
import com.kodcu.component.MyTab;
import com.kodcu.controller.ApplicationController;
import com.kodcu.other.Current;
import com.kodcu.service.shortcut.ShortcutProvider;
import de.jensd.fx.fontawesome.AwesomeIcon;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by usta on 25.12.2014.
 */
@Component
public class EditorService {

    private final Current current;
    private final ApplicationController controller;

    @Autowired
    public EditorService(final Current current, final ApplicationController controller) {
        this.current = current;
        this.controller = controller;
    }

    @Autowired
    private ShortcutProvider shortcutProvider;

    public Node createEditorVBox(EditorPane editorPane, MyTab myTab) {
        VBox vbox = new VBox();
        String iconSize = "14.0";
        double minSize = 14.01;


        final Label saveLabel = LabelBuilt.icon(AwesomeIcon.SAVE, iconSize, minSize)
                .clazz("top-label")
                .tip("Сохранить").click(controller::saveDoc).build();

        final Label newLabel = LabelBuilt.icon(AwesomeIcon.FILE_TEXT_ALT, iconSize, minSize)
                .clazz("top-label").tip("Новый файл").click(controller::newDoc).build();

        final Label openLabel = LabelBuilt.icon(AwesomeIcon.FOLDER_ALTPEN_ALT, iconSize, minSize)
                .clazz("top-label").tip("Открыть файл").click(controller::openDoc).build();

        final Label boldLabel = LabelBuilt.icon(AwesomeIcon.BOLD, iconSize, minSize)
                .clazz("top-label").tip("Жирный").click(event -> {
                    shortcutProvider.getProvider().addBold();
                }).build();

        final Label italicLabel = LabelBuilt.icon(AwesomeIcon.ITALIC, iconSize, minSize)
                .clazz("top-label").tip("Наклонный").click(event -> {
                    shortcutProvider.getProvider().addItalic();
                }).build();

        final Label headerLabel = LabelBuilt.icon(AwesomeIcon.HEADER, iconSize, minSize)
                .clazz("top-label").tip("Закгловки").click(event -> {
                    shortcutProvider.getProvider().addHeading();
                }).build();

        final Label codeLabel = LabelBuilt.icon(AwesomeIcon.CODE, iconSize, minSize)
                .clazz("top-label").tip("Пример кода").click(event -> {
                    shortcutProvider.getProvider().addCode("");
                }).build();

        final Label ulListLabel = LabelBuilt.icon(AwesomeIcon.LIST_UL, iconSize, minSize)
                .clazz("top-label").tip("Маркированный список").click(event -> {
                    shortcutProvider.getProvider().addUnorderedList();
                }).build();

        final Label olListLabel = LabelBuilt.icon(AwesomeIcon.LIST_ALTL, iconSize, minSize)
                .clazz("top-label").tip("Нумерованный список").click(event -> {
                    shortcutProvider.getProvider().addOrderedList();
                }).build();

        final Label tableLabel = LabelBuilt.icon(AwesomeIcon.TABLE, iconSize, minSize)
                .clazz("top-label").tip("Таблица").click(event -> {
                    shortcutProvider.getProvider().addTable();
                    ;
                }).build();

        final Label imageLabel = LabelBuilt.icon(AwesomeIcon.IMAGE, iconSize, minSize)
                .clazz("top-label").tip("Картинка").click(event -> {
                    shortcutProvider.getProvider().addImage();
                }).build();

        final Label subscriptLabel = LabelBuilt.icon(AwesomeIcon.SUBSCRIPT, iconSize, minSize)
                .clazz("top-label").tip("Нижний индекс").click(event -> {
                    shortcutProvider.getProvider().addSubscript();
                }).build();

        final Label superScriptLabel = LabelBuilt.icon(AwesomeIcon.SUPERSCRIPT, iconSize, minSize)
                .clazz("top-label").tip("Верхний индекс").click(event -> {
                    shortcutProvider.getProvider().addSuperscript();
                }).build();

        final Label underlineLabel = LabelBuilt.icon(AwesomeIcon.UNDERLINE, iconSize, minSize)
                .clazz("top-label").tip("Подчеркнутый").click(event -> {
                    shortcutProvider.getProvider().addUnderline();
                }).build();

        final Label hyperlinkLabel = LabelBuilt.icon(AwesomeIcon.LINK, iconSize, minSize)
                .clazz("top-label").tip("Ссылка").click(event -> {
                    shortcutProvider.getProvider().addHyperlink();
                }).build();

        final Label strikethroughLabel = LabelBuilt.icon(AwesomeIcon.STRIKETHROUGH, iconSize, minSize)
                .clazz("top-label").tip("Зачеркнутый").click(event -> {
                    shortcutProvider.getProvider().addStrike();
                }).build();

        final Label quoteLabel = LabelBuilt.icon(AwesomeIcon.QUOTE_LEFT, iconSize, minSize)
                .clazz("top-label").tip("Цитата").click(event -> {
                    shortcutProvider.getProvider().addQuote();
                }).build();

        final Label openMenuLabel = LabelBuilt.icon(AwesomeIcon.CHEVRON_CIRCLE_DOWN, iconSize, minSize)
                .clazz("top-label").tip("далее...").build();

        final Label showFileBrowser = LabelBuilt.icon(AwesomeIcon.CHEVRON_CIRCLE_RIGHT, iconSize, minSize)
                .click(event -> {
                    controller.showFileBrowser();
                })
                .clazz("top-label").tip("Показать панель файлов...").build();

        final Label showPreviewPanel = LabelBuilt.icon(AwesomeIcon.CHEVRON_CIRCLE_LEFT, iconSize, minSize)
                .click(event -> {
                    controller.showPreviewPanel();
                })
                .clazz("top-label").tip("Показать панель предв. просмотра...").build();

        showFileBrowser.visibleProperty().bindBidirectional(controller.fileBrowserVisibilityProperty());
        showFileBrowser.managedProperty().bindBidirectional(controller.fileBrowserVisibilityProperty());
        showPreviewPanel.visibleProperty().bindBidirectional(controller.previewPanelVisibilityProperty());
        showPreviewPanel.managedProperty().bindBidirectional(controller.previewPanelVisibilityProperty());

        Pane placeholderPane = new Pane();
        placeholderPane.maxWidth(Integer.MAX_VALUE);
        placeholderPane.prefHeight(1);
        placeholderPane.prefWidth(1);
        HBox.setHgrow(placeholderPane, Priority.ALWAYS);

        openMenuLabel.setOnMouseClicked(event -> {
            int childSize = vbox.getChildren().size();
            if (childSize == 2) {
                openMenuLabel.setText(AwesomeIcon.CHEVRON_CIRCLE_DOWN.toString());
                Tooltip.install(openMenuLabel, new Tooltip("далее..."));
                vbox.getChildren().remove(1);
            } else {
                openMenuLabel.setText(AwesomeIcon.CHEVRON_CIRCLE_UP.toString());
                openMenuLabel.getProperties().clear();
                vbox.getChildren().add(createSecondEditorVBox(iconSize, minSize));
            }
        });

        final ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setFocusTraversable(false);
        choiceBox.setManaged(true);
        choiceBox.setVisible(true);
        choiceBox.getItems().addAll("Asciidoc", "Markdown");
        choiceBox.getSelectionModel().selectFirst();

        myTab.setMarkup(choiceBox);

        final HBox topMenu = new HBox(
                showFileBrowser,
                newLabel,
                openLabel,
                saveLabel,
                boldLabel,
                italicLabel,
                underlineLabel,
                strikethroughLabel,
                headerLabel,
                hyperlinkLabel,
                quoteLabel,
                codeLabel,
                ulListLabel,
                olListLabel,
                tableLabel,
                imageLabel,
                subscriptLabel,
                superScriptLabel,
                choiceBox,
                openMenuLabel,
                placeholderPane,
                showPreviewPanel
        );

        topMenu.setAlignment(Pos.CENTER_LEFT);

        topMenu.setOnMouseClicked(event -> {
            editorPane.focus();
        });

        topMenu.setSpacing(9);
        topMenu.getStyleClass().add("top-menu");

        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(editorPane);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        vbox.getChildren().add(topMenu);
        return new VBox(vbox, scrollPane);
    }

    private Node createSecondEditorVBox(final String iconSize, final double minSize) {

        MenuButton admonitionButton = new MenuButton("Замечания");
        admonitionButton.setFocusTraversable(false);
        admonitionButton.getItems().add(new MenuItem("NOTE"));
        admonitionButton.getItems().add(new MenuItem("TIP"));
        admonitionButton.getItems().add(new MenuItem("IMPORTANT"));
        admonitionButton.getItems().add(new MenuItem("CAUTION"));
        admonitionButton.getItems().add(new MenuItem("WARNING"));

        admonitionButton.getItems().stream().forEach(item -> {
            item.setOnAction(e -> {
                shortcutProvider.getProvider().addAdmonition(item.getText());
            });
        });

// не хватает сноски, и выбор стилей

        MenuButton blocks = new MenuButton("Блоки");
        blocks.setFocusTraversable(false);
        blocks.getItems().add(MenuItemBuilt.item("Именованный блок").click(event -> {
            shortcutProvider.getProvider().addSidebarBlock();
        }));
        blocks.getItems().add(MenuItemBuilt.item("Пример").click(event -> {
            shortcutProvider.getProvider().addExampleBlock();
        }));
        blocks.getItems().add(MenuItemBuilt.item("не обрабатываемый текст").click(event -> {
            shortcutProvider.getProvider().addPassthroughBlock();
        }));
        blocks.getItems().add(MenuItemBuilt.item("Цитата").click(event -> {
            shortcutProvider.getProvider().addQuote();
        }));

        final MenuButton documentHelpers = new MenuButton("Части документа");
        documentHelpers.setFocusTraversable(false);
        documentHelpers.getItems().add(MenuItemBuilt.item("Книга").click(event -> {
            shortcutProvider.getProvider().addBookHeader();
        }));

        documentHelpers.getItems().add(MenuItemBuilt.item("Статья").click(event -> {
            shortcutProvider.getProvider().addArticleHeader();
        }));

        documentHelpers.getItems().add(MenuItemBuilt.item("Предисловие").click(event -> {
            shortcutProvider.getProvider().addPreface();
        }));

        documentHelpers.getItems().add(MenuItemBuilt.item("Посвящения").click(event -> {
            shortcutProvider.getProvider().addDedication();
        }));

        documentHelpers.getItems().add(MenuItemBuilt.item("Приложения").click(event -> {
            shortcutProvider.getProvider().addAppendix();
        }));

        documentHelpers.getItems().add(MenuItemBuilt.item("Глоссарий").click(event -> {
            shortcutProvider.getProvider().addGlossary();
        }));

        documentHelpers.getItems().add(MenuItemBuilt.item("Библиография").click(event -> {
            shortcutProvider.getProvider().addBibliography();
        }));

        documentHelpers.getItems().add(MenuItemBuilt.item("Концовка").click(event -> {
            shortcutProvider.getProvider().addColophon();
        }));

        documentHelpers.getItems().add(MenuItemBuilt.item("Индекс").click(event -> {
            shortcutProvider.getProvider().addIndex();
        }));

        final MenuButton extensions = new MenuButton("Расширения");
        extensions.setFocusTraversable(false);
        extensions.getItems().add(MenuItemBuilt.item("Mathjax").click(event -> {
            shortcutProvider.getProvider().addMathBlock();
        }));
        extensions.getItems().add(MenuItemBuilt.item("PlantUML").click(event -> {
            shortcutProvider.getProvider().addUmlBlock();
        }));

        extensions.getItems().add(MenuItemBuilt.item("Файловая система").click(event -> {
            shortcutProvider.getProvider().addTreeBlock();
        }));

        final MenuButton chartMenu = new MenuButton("Графики");
        chartMenu.setFocusTraversable(false);

        chartMenu.getItems().add(MenuItemBuilt.item("Pie").click(event -> {
            shortcutProvider.getProvider().addPieChart();
        }));

        chartMenu.getItems().add(MenuItemBuilt.item("Bar").click(event -> {
            shortcutProvider.getProvider().addBarChart();
        }));

        chartMenu.getItems().add(MenuItemBuilt.item("Line").click(event -> {
            shortcutProvider.getProvider().addLineChart();
        }));

        chartMenu.getItems().add(MenuItemBuilt.item("Area").click(event -> {
            shortcutProvider.getProvider().addAreaChart();
        }));

        chartMenu.getItems().add(MenuItemBuilt.item("Scatter").click(event -> {
            shortcutProvider.getProvider().addScatterChart();
        }));

        chartMenu.getItems().add(MenuItemBuilt.item("Bubble").click(event -> {
            shortcutProvider.getProvider().addBubbleChart();
        }));

        chartMenu.getItems().add(MenuItemBuilt.item("Stacked Area").click(event -> {
            shortcutProvider.getProvider().addStackedAreaChart();
        }));

        chartMenu.getItems().add(MenuItemBuilt.item("Stacked Bar").click(event -> {
            shortcutProvider.getProvider().addStackedBarChart();
        }));

        final HBox topMenu = new HBox(admonitionButton, blocks, documentHelpers, extensions,chartMenu);

        topMenu.setSpacing(9);
        topMenu.getStyleClass().add("top-menu");
        topMenu.setStyle("-fx-padding:0 10px 5px 10px;");

        return topMenu;
    }
}
