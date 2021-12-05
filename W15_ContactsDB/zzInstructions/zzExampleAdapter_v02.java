
// You can pass data by attaching a callback into your adapter:


// Create an interface:

interface OnTextClickListener {
    void onTextClick(ExampleItem data);
}

// Delegate the callback:

class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    List<ExampleItem> items;
    OnTextClickListener listener;

    ExampleAdapter(List<ExampleItem> items, OnTextClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder viewHolder, int position) {
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Say you want to pass an ExampleItem back to the fragment...
                ExampleItem data = items.get(position);
                listener.onTextClick(data);
            }
        });
    }
}


// Implement the fragment with the interface:

class YourFragment extends Fragment implements OnTextClickListener {

    public onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setAdapter(new ExampleAdapter(this));
    }

    void onTextClick(ExampleItem data) {
        // Now you can do however you want with the data here...
        Toast.makeText(getActivity(), "Got: " + data, Toast.LENGTH_SHORT).show()
    }
}
