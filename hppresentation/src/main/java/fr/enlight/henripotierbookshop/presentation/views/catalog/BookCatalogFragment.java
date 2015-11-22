package fr.enlight.henripotierbookshop.presentation.views.catalog;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import fr.enlight.henripotierbookshop.R;
import fr.enlight.henripotierbookshop.presentation.model.Book;
import fr.enlight.henripotierbookshop.presentation.presenter.BookCatalogPresenter;
import fr.enlight.henripotierbookshop.presentation.views.base.AbstractFragment;
import fr.enlight.henripotierbookshop.presentation.views.widget.GridDividerDecorator;
import fr.enlight.henripotierbookshop.presentation.views.widget.ItemDividerDecorator;

/**
 * This fragment purpose is to presents the book catalog to the user.
 */
public class BookCatalogFragment extends AbstractFragment implements BookCatalogPresenter.BookCatalogPresentableView {

    private OnBookCatalogInteractionListener bookCatalogInteractionListener;

    // The binding is made in the parent class
    @Bind(R.id.recyclerview_book_grid)
    RecyclerView recyclerView;

    BookCatalogAdapter bookCatalogAdapter;

    @Override
    protected int getLayoutInflateId() {
        return R.layout.fragment_book_catalog;
    }

    @Override
    protected void initViews(View view) {
        Context context = getActivity();

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            // If landscape, recyclerview is a horizontal list
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new ItemDividerDecorator(getActivity(), ItemDividerDecorator.HORIZONTAL_LIST,
                    ContextCompat.getDrawable(getContext(), R.drawable.transparent_divider)));
        } else {
            // If portrait, recyclerview is a grid with two columns
            recyclerView.setLayoutManager(new GridLayoutManager(context, getResources().getInteger(R.integer.grid_layout_span)));
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new GridDividerDecorator(context));
        }
        // Set adapter
        bookCatalogAdapter = new BookCatalogAdapter(context);
        bookCatalogAdapter.setInteractionListener(bookCatalogInteractionListener);

        recyclerView.setAdapter(bookCatalogAdapter);
    }

    // region interaction listener

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnBookCatalogInteractionListener){
            bookCatalogInteractionListener = (OnBookCatalogInteractionListener) context;
        }
    }

    @Override
    public void updateBookCatalog(List<Book> bookList) {
        if(bookCatalogAdapter != null){
            bookCatalogAdapter.updateBookList(bookList);
            bookCatalogAdapter.notifyDataSetChanged();
        }
    }

    /**
     * A listener used by the parent Activity to react on user interaction with this fragment.
     */
    public interface OnBookCatalogInteractionListener {

        /**
         * Called when the user has asked a book to be added to the cart.
         * @param book the concerned book
         */
        void onAddToCartSelected(Book book);
    }

    // endregion
}