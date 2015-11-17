package fr.enlight.henripotierbookshop.presentation.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.enlight.henripotierbookshop.R;
import fr.enlight.henripotierbookshop.presentation.model.Book;

/**
 * An adapter to presents a list of Book
 */
public class BookCatalogAdapter extends RecyclerView.Adapter<BookShortViewHolder>{

    private final Context context;
    private List<Book> bookList;

    public BookCatalogAdapter(Context context){
        this.context = context;
    }

    @Override
    public BookShortViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.cell_book_short, viewGroup, false);
        return new BookShortViewHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(BookShortViewHolder bookShortViewHolder, int position) {
        if(bookList != null){
            bookShortViewHolder.setBook(bookList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(bookList == null){
            return 0;
        }
        return bookList.size();
    }

    public void updateBookList(List<Book> newBooks){
        bookList = newBooks;
    }
}
