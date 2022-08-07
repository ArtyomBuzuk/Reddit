package com.artyombuzuk.reddit.view

import android.app.ProgressDialog.show
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.artyombuzuk.reddit.R
import com.artyombuzuk.reddit.databinding.FragmentPostDetailsBinding
import com.artyombuzuk.reddit.utils.show
import com.artyombuzuk.reddit.model.RedditPost
import com.artyombuzuk.reddit.view.viewmodel.TopRedditPostDetailsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopRedditPostDetailsFragment : BaseFragment() {

    private var _binding: FragmentPostDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TopRedditPostDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailsBinding.inflate(inflater)

        arguments?.getParcelable<RedditPost>(PARAMETER_REDDIT_POST_KEY)?.let { redditPost ->
            binding.redditPostTitle.text = redditPost.title
            binding.redditPostDescription.text = redditPost.name
            binding.author.text = redditPost.author
            binding.buttonBack.setOnClickListener { onBackPressed() }
            binding.shareButton.setOnClickListener {
                shareUrl(redditPost.url ?: "")
            }
            binding.redditPostUrl.apply {
                text = getString(R.string.top_reddit_post_url, redditPost.url ?: "")
                setOnClickListener { shareUrl(redditPost.url ?: "") }
            }
            if (viewModel.shouldLoadImage(redditPost)) {
                Glide.with(this)
                    .asBitmap()
                    .load(redditPost.thumbnail)
                    .centerCrop()
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            binding.thumbnail.setImageBitmap(resource)
                            binding.saveButton.apply {
                                show()
                                setOnClickListener {
                                    saveImageOnGallery(resource)
                                }

                            }
                            binding.saveButton.apply {
                                show()
                                setOnClickListener {
                                    saveImageOnGallery(resource)
                                }
                            }
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            // do nothing
                        }

                    })
            }
        }

        return binding.root
    }

    private val backPressedDispatcher = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            this@TopRedditPostDetailsFragment.onBackPressed()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        (requireActivity() as MainActivity).apply {
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedDispatcher)
            setupActionBarWithNavController(findNavController())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            this.onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }

    private fun shareUrl(url: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
    private fun saveImageOnGallery(bitmap: Bitmap) {
        requireActivity().contentResolver
        val savedImageURL = MediaStore.Images.Media.insertImage(
            requireActivity().contentResolver,
            bitmap,
            bitmap.toString(),
            "Image"
        )
        val success = savedImageURL != null
        showToastSaveImage(success)
    }

    private fun showToastSaveImage(success: Boolean) {
        Toast.makeText(context, if (success) R.string.top_reddit_post_save_image_success else R.string.top_reddit_post_save_image_error, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        backPressedDispatcher.remove()
        super.onDestroyView()
    }


    companion object {

        const val TAG = "TopRedditPostDetailsFragment"
        const val PARAMETER_REDDIT_POST_KEY = "PARAMETER_REDDIT_POST_KEY"
        fun newInstance(): TopRedditPostDetailsFragment = TopRedditPostDetailsFragment()
    }


}